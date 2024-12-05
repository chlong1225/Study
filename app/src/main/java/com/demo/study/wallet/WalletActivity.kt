package com.demo.study.wallet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.chl.common.utils.LogUtil
import com.demo.study.BaseActivity
import com.demo.study.databinding.ActivityWalletBinding
import com.demo.study.wallet.bull.BullContractAbi
import com.demo.study.wallet.usdt.ContractAbi
import org.bitcoinj.core.Base58
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.Bool
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.Type
import org.web3j.abi.datatypes.generated.Uint256
import org.web3j.crypto.Bip32ECKeyPair
import org.web3j.crypto.Bip44WalletUtils
import org.web3j.crypto.Credentials
import org.web3j.crypto.Hash
import org.web3j.crypto.MnemonicUtils
import org.web3j.crypto.RawTransaction
import org.web3j.crypto.TransactionEncoder
import org.web3j.crypto.WalletUtils
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.core.methods.response.EthCall
import org.web3j.protocol.http.HttpService
import org.web3j.tx.Contract
import org.web3j.tx.RawTransactionManager
import org.web3j.tx.gas.DefaultGasProvider
import org.web3j.utils.Convert
import org.web3j.utils.Numeric
import java.io.File
import java.math.BigInteger
import java.security.SecureRandom

/**
 * create on 2024/12/2
 * @author chenglong
 * description :
 */
class WalletActivity : BaseActivity<ActivityWalletBinding>() {

    companion object{

        private const val SALT = ""
        //同步欧易的助记词
        private val mnemonic = "mystery diamond supreme office violin circle dune brush kid giggle useful bomb"

        //节点
//        private val eth_url = "https://rpc.sepolia.org/"
        private val eth_url = "https://sepolia.infura.io/v3/ed257b038fdf462ebb43a33f967277c9"
        private const val sepolia_eth_chainId = 11155111L

        private val avax_url = "https://ava-testnet.public.blastapi.io/ext/bc/C/rpc"
        private const val avax_chainId = 43113L

        private val bnb_url = "https://data-seed-prebsc-1-s1.binance.org:8545/"
        private const val bnb_chainId = 97L

        private val pol_url = "https://polygon-amoy.drpc.org"
        private const val pol_chainId = 80002L

        //钱包地址
        private const val ower_address = "0x980e77e6ae3efb8d4889c84c8644611a087d192e"

        //接收者的地址
//        private val pay_address = "0xef8cd17ce6b73b552181c46d9a95974c9c0dd1a3"
        private val pay_address = "0x08aa84c00328a939aa53e04f8884a5d395042453"

        //USD合约的地址
        private const val usd_path = "0xF4bB9F6634b7228ede7F0252771015ca193853Fa"

        //BULL合约的地址
        private const val bull_path = "0xd1092BAAf1ff6EB9305f035C4805092eb9cCa761"

        fun openSelf(ctx: Context) {
            val intent = Intent(ctx, WalletActivity::class.java)
            ctx.startActivity(intent)
        }
    }

    private var crash: Credentials? = null

    override fun buildViewBinding(): ActivityWalletBinding {
        return ActivityWalletBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewBinding().btnWallet.setOnClickListener {
            buildWallet()
        }
        getViewBinding().btnTron1.setOnClickListener {
            buildTronAddress()
        }
        getViewBinding().btnEthBalance.setOnClickListener {
            queryEthBalance()
        }
        getViewBinding().btnEthPay.setOnClickListener {
            payEth()
        }
        getViewBinding().btnUsdtBalance1.setOnClickListener {
            queryUsdTBalance1()
        }
        getViewBinding().btnUsdtPay1.setOnClickListener {
            payUsdT1()
        }
        getViewBinding().btnUsdtApprove1.setOnClickListener {
            approveUsdT1()
        }
        getViewBinding().btnUsdtApprovePay1.setOnClickListener {
            payApproveUsdT1()
        }
        getViewBinding().btnUsdtBalance2.setOnClickListener {
            queryUsdTBalance2()
        }
        getViewBinding().btnUsdtPay2.setOnClickListener {
            payUsdT2()
        }
        getViewBinding().btnUsdtApprove2.setOnClickListener {
            approveUsdT2()
        }
        getViewBinding().btnUsdtApprovePay2.setOnClickListener {
            payApproveUsdT2()
        }
        getViewBinding().btnBullBalance1.setOnClickListener {
            queryBullBalance1()
        }
        getViewBinding().btnBullPay1.setOnClickListener {
            payBull1()
        }
        getViewBinding().btnBullApprove1.setOnClickListener {
            approveBull1()
        }
        getViewBinding().btnBullApprovePay1.setOnClickListener {
            payApproveBull1()
        }
        getViewBinding().btnBullBalance2.setOnClickListener {
            queryBullBalance2()
        }
        getViewBinding().btnBullPay2.setOnClickListener {
            payBull2()
        }
        getViewBinding().btnBullApprove2.setOnClickListener {
            approveBull2()
        }
        getViewBinding().btnBullApprovePay2.setOnClickListener {
            payApproveBull2()
        }
        getViewBinding().btnAvaxBalance.setOnClickListener {
            queryAvaxBalance()
        }
        getViewBinding().btnAvaxPay.setOnClickListener {
            payAvax()
        }
        getViewBinding().btnBnbBalance.setOnClickListener {
            queryBnbBalance()
        }
        getViewBinding().btnBnbPay.setOnClickListener {
            payBnb()
        }
        getViewBinding().btnPolBalance.setOnClickListener {
            queryPolBalance()
        }
        getViewBinding().btnPolPay.setOnClickListener {
            payPol()
        }

    }

    private fun payPol() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        val amount = getViewBinding().etPolAmount.text.trim().toString()
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(this, "请先输入交易金额", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            val web3j = Web3j.build(HttpService(pol_url))

            //交易参数
            val amountInWei = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger()
            val gasPrice = web3j.ethGasPrice().send().gasPrice
            val nonce = web3j.ethGetTransactionCount(crash!!.address, DefaultBlockParameterName.PENDING).send().transactionCount
            LogUtil.e("AAAA", "payPol ： nonce = $nonce ;; gasPrice = $gasPrice ;; default = ${Contract.GAS_PRICE}")

            val balance = web3j.ethGetBalance(crash!!.address, DefaultBlockParameterName.LATEST).send().balance
            val bigDecimal = Convert.fromWei(balance.toString(), Convert.Unit.ETHER)
            LogUtil.e("AAAA", "payPol ： balance = $balance ;; bigDecimal = $bigDecimal")
            if (balance < amountInWei) {
                runOnUiThread {
                    Toast.makeText(this, "当前余额不够", Toast.LENGTH_SHORT).show()
                }
                return@Thread
            }

            //创建交易
            val rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, BigInteger.valueOf(430000), pay_address, amountInWei)

            //签署交易
            val signedMessage = TransactionEncoder.signMessage(rawTransaction, pol_chainId, crash)
            //交易编码
            val hexValue = Numeric.toHexString(signedMessage)

            //发送交易
            val ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send()

            if (ethSendTransaction.hasError()) {
                LogUtil.e("AAAA", "payPol ： fail = ${ethSendTransaction.error.message}")
            } else {
                LogUtil.e("AAAA", "payPol ： success = ${ethSendTransaction.transactionHash}")
            }
        }.start()
    }

    private fun queryPolBalance() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            val web3j = Web3j.build(HttpService(pol_url))
            val balance = web3j.ethGetBalance(crash!!.address, DefaultBlockParameterName.LATEST).send().balance
            val bigDecimal = Convert.fromWei(balance.toString(), Convert.Unit.ETHER)
            LogUtil.e("AAAA","queryPolBalance : balance = $balance ;; bigDecimal = $bigDecimal")
            runOnUiThread {
                getViewBinding().tvPolBalance.text = bigDecimal.toString() + "POL"
            }
        }.start()
    }

    private fun payBnb() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        val amount = getViewBinding().etBnbAmount.text.trim().toString()
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(this, "请先输入交易金额", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            val web3j = Web3j.build(HttpService(bnb_url))

            //交易参数
            val amountInWei = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger()
            val gasPrice = web3j.ethGasPrice().send().gasPrice
            val nonce = web3j.ethGetTransactionCount(crash!!.address, DefaultBlockParameterName.PENDING).send().transactionCount
            LogUtil.e("AAAA", "payBnb ： nonce = $nonce ;; gasPrice = $gasPrice ;; default = ${Contract.GAS_PRICE}")

            val balance = web3j.ethGetBalance(crash!!.address, DefaultBlockParameterName.LATEST).send().balance
            val bigDecimal = Convert.fromWei(balance.toString(), Convert.Unit.ETHER)
            LogUtil.e("AAAA", "payBnb ： balance = $balance ;; bigDecimal = $bigDecimal")
            if (balance < amountInWei) {
                runOnUiThread {
                    Toast.makeText(this, "当前余额不够", Toast.LENGTH_SHORT).show()
                }
                return@Thread
            }

            //创建交易
            val rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, BigInteger.valueOf(430000), pay_address, amountInWei)

            //签署交易
            val signedMessage = TransactionEncoder.signMessage(rawTransaction, bnb_chainId, crash)
            //交易编码
            val hexValue = Numeric.toHexString(signedMessage)

            //发送交易
            val ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send()

            if (ethSendTransaction.hasError()) {
                LogUtil.e("AAAA", "payBnb ： fail = ${ethSendTransaction.error.message}")
            } else {
                LogUtil.e("AAAA", "payBnb ： success = ${ethSendTransaction.transactionHash}")
            }
        }.start()
    }

    private fun queryBnbBalance() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            val web3j = Web3j.build(HttpService(bnb_url))
            val balance = web3j.ethGetBalance(crash!!.address, DefaultBlockParameterName.LATEST).send().balance
            val bigDecimal = Convert.fromWei(balance.toString(), Convert.Unit.ETHER)
            LogUtil.e("AAAA","queryBnbBalance : balance = $balance ;; bigDecimal = $bigDecimal")
            runOnUiThread {
                getViewBinding().tvBnbBalance.text = bigDecimal.toString() + "BNB"
            }
        }.start()
    }

    /**
     * AVAX币的交易
     */
    private fun payAvax() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        val amount = getViewBinding().etAvaxAmount.text.trim().toString()
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(this, "请先输入交易金额", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            val web3j = Web3j.build(HttpService(avax_url))

            //交易参数
            val amountInWei = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger()
            val gasPrice = web3j.ethGasPrice().send().gasPrice
            val nonce = web3j.ethGetTransactionCount(crash!!.address, DefaultBlockParameterName.PENDING).send().transactionCount
            LogUtil.e("AAAA", "payAvax ： nonce = $nonce ;; gasPrice = $gasPrice")

            val balance = web3j.ethGetBalance(crash!!.address, DefaultBlockParameterName.LATEST).send().balance
            val bigDecimal = Convert.fromWei(balance.toString(), Convert.Unit.ETHER)
            LogUtil.e("AAAA", "payAvax ： balance = $balance ;; bigDecimal = $bigDecimal")
            if (balance < amountInWei) {
                runOnUiThread {
                    Toast.makeText(this, "当前余额不够", Toast.LENGTH_SHORT).show()
                }
                return@Thread
            }

            //创建交易
            val rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, Contract.GAS_LIMIT, pay_address, amountInWei)

            //签署交易
            val signedMessage = TransactionEncoder.signMessage(rawTransaction, avax_chainId, crash)
            //交易编码
            val hexValue = Numeric.toHexString(signedMessage)

            //发送交易
            val ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send()

            if (ethSendTransaction.hasError()) {
                LogUtil.e("AAAA", "payAvax ： fail = ${ethSendTransaction.error.message}")
            } else {
                LogUtil.e("AAAA", "payAvax ： success = ${ethSendTransaction.transactionHash}")
            }
        }.start()
    }

    /**
     * 查询AVAX币的数量
     */
    private fun queryAvaxBalance() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            val web3j = Web3j.build(HttpService(avax_url))
            val balance = web3j.ethGetBalance(crash!!.address, DefaultBlockParameterName.LATEST).send().balance
            val bigDecimal = Convert.fromWei(balance.toString(), Convert.Unit.ETHER)
            LogUtil.e("AAAA","queryAvaxBalance : balance = $balance ;; bigDecimal = $bigDecimal")
            runOnUiThread {
                getViewBinding().tvAvaxBalance.text = bigDecimal.toString()
            }
        }.start()
    }

    private fun payApproveBull2() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        val amount = getViewBinding().etBullAmount.text.trim().toString()
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(this, "请先输入支付金额", Toast.LENGTH_SHORT).show()
            return
        }
        Thread {
            try {
                val web3j = Web3j.build(HttpService(eth_url))
                val bullContractAbi = BullContractAbi.load(bull_path, web3j, RawTransactionManager(web3j, crash!!, 11155111), object : DefaultGasProvider() {})
                val amountInWei = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger()
                val transferFrom = bullContractAbi.transferFrom(ower_address, pay_address, amountInWei).send()
                LogUtil.e("AAAA","payApproveBull2 : hash = ${transferFrom.blockHash} ;; state = ${transferFrom.isStatusOK}")
            } catch (e: Exception) {
                LogUtil.e("AAAA", "payApproveBull2 : error = ${e.message}")
            }
        }.start()
    }

    private fun approveBull2() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        val amount = getViewBinding().etBullAmount.text.trim().toString()
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(this, "请先输入授权金额", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            try {
                val web3j = Web3j.build(HttpService(eth_url))
                val bullContractAbi = BullContractAbi.load(bull_path, web3j, RawTransactionManager(web3j, crash!!, 11155111), object : DefaultGasProvider() {})
                val amountInWei = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger()
                val approve = bullContractAbi.approve(ower_address, amountInWei).send()
                LogUtil.e("AAAA","approveBull2 : hash = ${approve.blockHash} ;; state = ${approve.isStatusOK}")
            } catch (e: Exception) {
                LogUtil.e("AAAA", "approveBull2 : error = ${e.message}")
            }
        }.start()
    }

    private fun payBull2() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        val amount = getViewBinding().etBullAmount.text.trim().toString()
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(this, "请先输入交易金额", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            try {
                val web3j = Web3j.build(HttpService(eth_url))
                val bullContractAbi = BullContractAbi.load(bull_path, web3j, RawTransactionManager(web3j, crash!!, 11155111), object : DefaultGasProvider() {})
                val amountInWei = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger()
                val transfer = bullContractAbi.transfer(pay_address, amountInWei).send()
                LogUtil.e("AAAA","payBull2 : hash = ${transfer.blockHash} ;; state = ${transfer.isStatusOK}")
            } catch (e: Exception) {
                LogUtil.e("AAAA", "payBull2 : error = ${e.message}")
            }
        }.start()
    }

    private fun queryBullBalance2() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            val web3j = Web3j.build(HttpService(eth_url))
            val contractAbi = ContractAbi.load(bull_path, web3j, RawTransactionManager(web3j, crash!!, sepolia_eth_chainId), object : DefaultGasProvider() {})
            val value = contractAbi.balanceOf(ower_address).send()
            val balance = Convert.fromWei(value.toString(), Convert.Unit.ETHER)
            LogUtil.e("AAAA", "queryBullBalance2 : balance = $balance ;; value = $value")
            runOnUiThread {
                getViewBinding().tvBullBalance.text = balance.toString()
            }
        }.start()
    }

    private fun payApproveBull1() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        val amount = getViewBinding().etBullAmount.text.trim().toString()
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(this, "请先输入交易金额", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            val amountInWei = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger()
            val transferFrom = Function(
                "transferFrom", listOf<Type<*>>(Address(ower_address),Address(pay_address),Uint256(amountInWei)), listOf<TypeReference<*>>(object : TypeReference<Bool?>() {})
            )
            val data = FunctionEncoder.encode(transferFrom)

            val web3j = Web3j.build(HttpService(eth_url))

            //交易参数

            val nonce = web3j.ethGetTransactionCount(crash!!.address, DefaultBlockParameterName.PENDING).send().transactionCount
            LogUtil.e("AAAA","payApproveBull1 : nonce = $nonce")

            //创建交易
            val rawTransaction = RawTransaction.createTransaction(nonce, Contract.GAS_PRICE, Contract.GAS_LIMIT, bull_path, data)

            //签署交易
            val signedMessage = TransactionEncoder.signMessage(rawTransaction, sepolia_eth_chainId, crash)
            //交易编码
            val hexValue = Numeric.toHexString(signedMessage)

            //发送交易
            val ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send()

            if (ethSendTransaction.hasError()) {
                LogUtil.e("AAAA", "payApproveBull1 : fail = ${ethSendTransaction.error.message}")
            } else {
                LogUtil.e("AAAA", "payApproveBull1 : success = ${ethSendTransaction.transactionHash}")
            }
        }.start()
    }

    private fun approveBull1() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        val amount = getViewBinding().etBullAmount.text.trim().toString()
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(this, "请先输入授权金额", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            val amountInWei = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger()
            val transferFrom = Function(
                "approve", listOf<Type<*>>(Address(ower_address),Uint256(amountInWei)), listOf<TypeReference<*>>(object : TypeReference<Bool?>() {})
            )
            val data = FunctionEncoder.encode(transferFrom)

            val web3j = Web3j.build(HttpService(eth_url))

            //交易参数

            val nonce = web3j.ethGetTransactionCount(crash!!.address, DefaultBlockParameterName.PENDING).send().transactionCount
            LogUtil.e("AAAA","approveBull1 : nonce = $nonce")

            //创建交易
            val rawTransaction = RawTransaction.createTransaction(nonce, Contract.GAS_PRICE, Contract.GAS_LIMIT, bull_path, data)

            //签署交易
            val signedMessage = TransactionEncoder.signMessage(rawTransaction, sepolia_eth_chainId, crash)
            //交易编码
            val hexValue = Numeric.toHexString(signedMessage)

            //发送交易
            val ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send()

            if (ethSendTransaction.hasError()) {
                LogUtil.e("AAAA", "approveBull1 : fail = ${ethSendTransaction.error.message}")
            } else {
                LogUtil.e("AAAA", "approveBull1 : success = ${ethSendTransaction.transactionHash}")
            }
        }.start()
    }

    private fun payBull1() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        val amount = getViewBinding().etBullAmount.text.trim().toString()
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(this, "请先输入交易金额", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            val amountInWei = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger()
            val transfer = Function(
                "transfer", listOf<Type<*>>(Address(pay_address),Uint256(amountInWei)), listOf<TypeReference<*>>(object : TypeReference<Bool?>() {})
            )
            val data = FunctionEncoder.encode(transfer)

            val web3j = Web3j.build(HttpService(eth_url))

            //交易参数

            val nonce = web3j.ethGetTransactionCount(crash!!.address, DefaultBlockParameterName.PENDING).send().transactionCount
            LogUtil.e("AAAA","payBull1 : nonce = $nonce")

            //创建交易
            val rawTransaction = RawTransaction.createTransaction(nonce, Contract.GAS_PRICE, Contract.GAS_LIMIT, bull_path, data)

            //签署交易
            val signedMessage = TransactionEncoder.signMessage(rawTransaction, sepolia_eth_chainId, crash)
            //交易编码
            val hexValue = Numeric.toHexString(signedMessage)

            //发送交易
            val ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send()

            if (ethSendTransaction.hasError()) {
                LogUtil.e("AAAA", "payBull1 : fail = ${ethSendTransaction.error.message}")
            } else {
                LogUtil.e("AAAA", "payBull1 : success = ${ethSendTransaction.transactionHash}")
            }
        }.start()
    }

    private fun queryBullBalance1() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            val web3j = Web3j.build(HttpService(eth_url))
            val balanceOf = Function(
                "balanceOf", listOf<Type<*>>(Address(ower_address)), listOf<TypeReference<*>>(object : TypeReference<Uint256?>() {})
            ) // 返回类型

            // 将函数调用编码为数据
            val data = FunctionEncoder.encode(balanceOf)

            // 创建一个 EthCall 请求
            val ethCall: EthCall = web3j.ethCall(
                Transaction.createEthCallTransaction(ower_address, bull_path, data),
                DefaultBlockParameterName.LATEST
            ).send()
            val value = BigInteger(ethCall.value.substring(2), 16)
            val balance = Convert.fromWei(value.toString(), Convert.Unit.ETHER)
            LogUtil.e("AAAA", "queryBullBalance1 : balance = $balance ;; value = $value")
            runOnUiThread {
                getViewBinding().tvBullBalance.text = balance.toString()
            }
        }.start()
    }

    private fun payApproveUsdT2() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        val amount = getViewBinding().etUsdtAmount.text.trim().toString()
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(this, "请先输入支付金额", Toast.LENGTH_SHORT).show()
            return
        }
        Thread {
            try {
                val web3j = Web3j.build(HttpService(eth_url))
                val contractAbi = ContractAbi.load(usd_path, web3j, RawTransactionManager(web3j, crash!!, 11155111), object : DefaultGasProvider() {})
                val amountInWei = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger()
                val transferFrom = contractAbi.transferFrom(ower_address, pay_address, amountInWei).send()
                LogUtil.e("AAAA","payApproveUsdT2 : hash = ${transferFrom.blockHash} ;; state = ${transferFrom.isStatusOK}")
            } catch (e: Exception) {
                LogUtil.e("AAAA", "payApproveUsdT2 : error = ${e.message}")
            }
        }.start()
    }

    private fun approveUsdT2() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        val amount = getViewBinding().etUsdtAmount.text.trim().toString()
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(this, "请先输入授权金额", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            try {
                val web3j = Web3j.build(HttpService(eth_url))
                val contractAbi = ContractAbi.load(usd_path, web3j, RawTransactionManager(web3j, crash!!, 11155111), object : DefaultGasProvider() {})
                val amountInWei = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger()
                val approve = contractAbi.approve(ower_address, amountInWei).send()
                LogUtil.e("AAAA","approveUsdT2 : hash = ${approve.blockHash} ;; state = ${approve.isStatusOK}")
            } catch (e: Exception) {
                LogUtil.e("AAAA", "approveUsdT2 : error = ${e.message}")
            }
        }.start()
    }

    private fun payUsdT2() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        val amount = getViewBinding().etUsdtAmount.text.trim().toString()
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(this, "请先输入交易金额", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            try {
                val web3j = Web3j.build(HttpService(eth_url))
                val contractAbi = ContractAbi.load(usd_path, web3j, RawTransactionManager(web3j, crash!!, 11155111), object : DefaultGasProvider() {})
                val amountInWei = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger()
                val transfer = contractAbi.transfer(pay_address, amountInWei).send()
                LogUtil.e("AAAA","payUsdT2 : hash = ${transfer.blockHash} ;; state = ${transfer.isStatusOK}")
            } catch (e: Exception) {
                LogUtil.e("AAAA", "payUsdT2 : error = ${e.message}")
            }
        }.start()
    }

    private fun queryUsdTBalance2() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            val web3j = Web3j.build(HttpService(eth_url))
            val contractAbi = ContractAbi.load(usd_path, web3j, RawTransactionManager(web3j, crash!!, sepolia_eth_chainId), object : DefaultGasProvider() {})
            val value = contractAbi.balanceOf(ower_address).send()
            val balance = Convert.fromWei(value.toString(), Convert.Unit.ETHER)
            LogUtil.e("AAAA", "queryUsdTBalance2 : balance = $balance ;; value = $value")
            runOnUiThread {
                getViewBinding().tvUsdtBalance.text = balance.toString()
            }
        }.start()
    }

    /**
     * UsdT授权交易。注意：会覆盖之前的授权，不是叠加
     */
    private fun payApproveUsdT1() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        val amount = getViewBinding().etUsdtAmount.text.trim().toString()
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(this, "请先输入交易金额", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            val amountInWei = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger()
            val transferFrom = Function(
                "transferFrom", listOf<Type<*>>(Address(ower_address),Address(pay_address),Uint256(amountInWei)), listOf<TypeReference<*>>(object : TypeReference<Bool?>() {})
            )
            val data = FunctionEncoder.encode(transferFrom)

            val web3j = Web3j.build(HttpService(eth_url))

            //交易参数

            val nonce = web3j.ethGetTransactionCount(crash!!.address, DefaultBlockParameterName.PENDING).send().transactionCount
            LogUtil.e("AAAA","payApproveUsdT1 : nonce = $nonce")

            //创建交易
            val rawTransaction = RawTransaction.createTransaction(nonce, Contract.GAS_PRICE, Contract.GAS_LIMIT, usd_path, data)

            //签署交易
            val signedMessage = TransactionEncoder.signMessage(rawTransaction, sepolia_eth_chainId, crash)
            //交易编码
            val hexValue = Numeric.toHexString(signedMessage)

            //发送交易
            val ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send()

            if (ethSendTransaction.hasError()) {
                LogUtil.e("AAAA", "payApproveUsdT1 : fail = ${ethSendTransaction.error.message}")
            } else {
                LogUtil.e("AAAA", "payApproveUsdT1 : success = ${ethSendTransaction.transactionHash}")
            }
        }.start()
    }


    /**
     * 授权UsdT
     */
    private fun approveUsdT1() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        val amount = getViewBinding().etUsdtAmount.text.trim().toString()
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(this, "请先输入授权金额", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            val amountInWei = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger()
            val transferFrom = Function(
                "approve", listOf<Type<*>>(Address(ower_address),Uint256(amountInWei)), listOf<TypeReference<*>>(object : TypeReference<Bool?>() {})
            )
            val data = FunctionEncoder.encode(transferFrom)

            val web3j = Web3j.build(HttpService(eth_url))

            //交易参数

            val nonce = web3j.ethGetTransactionCount(crash!!.address, DefaultBlockParameterName.PENDING).send().transactionCount
            LogUtil.e("AAAA","approveUsdT1 : nonce = $nonce")

            //创建交易
            val rawTransaction = RawTransaction.createTransaction(nonce, Contract.GAS_PRICE, Contract.GAS_LIMIT, usd_path, data)

            //签署交易
            val signedMessage = TransactionEncoder.signMessage(rawTransaction, sepolia_eth_chainId, crash)
            //交易编码
            val hexValue = Numeric.toHexString(signedMessage)

            //发送交易
            val ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send()

            if (ethSendTransaction.hasError()) {
                LogUtil.e("AAAA", "approveUsdT1 : fail = ${ethSendTransaction.error.message}")
            } else {
                LogUtil.e("AAAA", "approveUsdT1 : success = ${ethSendTransaction.transactionHash}")
            }
        }.start()
    }

    /**
     * 交易UsdT
     */
    private fun payUsdT1() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        val amount = getViewBinding().etUsdtAmount.text.trim().toString()
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(this, "请先输入交易金额", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            val amountInWei = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger()
            val transfer = Function(
                "transfer", listOf<Type<*>>(Address(pay_address),Uint256(amountInWei)), listOf<TypeReference<*>>(object : TypeReference<Bool?>() {})
            )
            val data = FunctionEncoder.encode(transfer)

            val web3j = Web3j.build(HttpService(eth_url))

            //交易参数

            val nonce = web3j.ethGetTransactionCount(crash!!.address, DefaultBlockParameterName.PENDING).send().transactionCount
            LogUtil.e("AAAA","payUsdT1 : nonce = $nonce")

            //创建交易
            val rawTransaction = RawTransaction.createTransaction(nonce, Contract.GAS_PRICE, Contract.GAS_LIMIT, usd_path, data)

            //签署交易
            val signedMessage = TransactionEncoder.signMessage(rawTransaction, sepolia_eth_chainId, crash)
            //交易编码
            val hexValue = Numeric.toHexString(signedMessage)

            //发送交易
            val ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send()

            if (ethSendTransaction.hasError()) {
                LogUtil.e("AAAA", "payUsdT1 : fail = ${ethSendTransaction.error.message}")
            } else {
                LogUtil.e("AAAA", "payUsdT1 : success = ${ethSendTransaction.transactionHash}")
            }
        }.start()
    }

    /**
     * 查询UsdT的余额
     */
    private fun queryUsdTBalance1() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            val web3j = Web3j.build(HttpService(eth_url))
            val balanceOf = Function(
                "balanceOf", listOf<Type<*>>(Address(ower_address)), listOf<TypeReference<*>>(object : TypeReference<Uint256?>() {})
            ) // 返回类型

            // 将函数调用编码为数据
            val data = FunctionEncoder.encode(balanceOf)

            // 创建一个 EthCall 请求
            val ethCall: EthCall = web3j.ethCall(
                Transaction.createEthCallTransaction(ower_address, usd_path, data),
                DefaultBlockParameterName.LATEST
            ).send()
            val value = BigInteger(ethCall.value.substring(2), 16)
            val balance = Convert.fromWei(value.toString(), Convert.Unit.ETHER)
            LogUtil.e("AAAA", "queryUsdTBalance1 : balance = $balance ;; value = $value")
            runOnUiThread {
                getViewBinding().tvUsdtBalance.text = balance.toString()
            }
        }.start()
    }

    /**
     * 交易ETH
     */
    private fun payEth() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        val amount = getViewBinding().etEthAmount.text.trim().toString()
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(this, "请先输入交易金额", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            val web3j = Web3j.build(HttpService(eth_url))

            //交易参数
            val amountInWei = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger()
            val gasPrice = web3j.ethGasPrice().send().gasPrice
            val nonce = web3j.ethGetTransactionCount(crash!!.address, DefaultBlockParameterName.PENDING).send().transactionCount
            LogUtil.e("AAAA", "payEth ： nonce = $nonce ;; gasPrice = $gasPrice")

            val balance = web3j.ethGetBalance(crash!!.address, DefaultBlockParameterName.LATEST).send().balance
            val bigDecimal = Convert.fromWei(balance.toString(), Convert.Unit.ETHER)
            LogUtil.e("AAAA", "payEth ： balance = $balance ;; bigDecimal = $bigDecimal")
            if (balance < amountInWei) {
                runOnUiThread {
                    Toast.makeText(this, "当前余额不够", Toast.LENGTH_SHORT).show()
                }
                return@Thread
            }

            //创建交易
            val rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, Contract.GAS_LIMIT, pay_address, amountInWei)

            //签署交易
            val signedMessage = TransactionEncoder.signMessage(rawTransaction, sepolia_eth_chainId, crash)
            //交易编码
            val hexValue = Numeric.toHexString(signedMessage)

            //发送交易
            val ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send()

            if (ethSendTransaction.hasError()) {
                LogUtil.e("AAAA", "payEth ： fail = ${ethSendTransaction.error.message}")
            } else {
                LogUtil.e("AAAA", "payEth ： success = ${ethSendTransaction.transactionHash}")
            }
        }.start()
    }

    /**
     * 茶轩ETH的余额
     */
    private fun queryEthBalance() {
        if (crash == null) {
            Toast.makeText(this, "请先创建钱包", Toast.LENGTH_SHORT).show()
            return
        }
        Thread{
            val web3j = Web3j.build(HttpService(eth_url))
            val balance = web3j.ethGetBalance(crash!!.address, DefaultBlockParameterName.LATEST).send().balance
            val bigDecimal = Convert.fromWei(balance.toString(), Convert.Unit.ETHER)
            LogUtil.e("AAAA","queryEthBalance : balance = $balance ;; bigDecimal = $bigDecimal")
            runOnUiThread {
                getViewBinding().tvEthBalance.text = bigDecimal.toString()
            }
        }.start()
    }

    /**
     * 生成波场TRON的地址
     */
    private fun buildTronAddress() {
        if (crash == null) {
            return
        }
        Thread{
            val seed = MnemonicUtils.generateSeed(mnemonic, SALT)
            val masterKeypair = Bip32ECKeyPair.generateKeyPair(seed)
            // m/44'/60'/0'/0/0
            val path = intArrayOf(44 or Bip32ECKeyPair.HARDENED_BIT, 195 or Bip32ECKeyPair.HARDENED_BIT, 0 or Bip32ECKeyPair.HARDENED_BIT, 0, 0)
            val bip44Keypair = Bip32ECKeyPair.deriveKeyPair(masterKeypair, path)
            val create = Credentials.create(bip44Keypair)
            val pri = create.ecKeyPair.privateKey.toString(16)
            val pub = create.ecKeyPair.publicKey.toString(16)
            val addressByte = Numeric.hexStringToByteArray("0x41" + create.address.substring(2))
            val firstHash = Hash.sha256(addressByte)
            val secondHash = Hash.sha256(firstHash)
            val tronHash = ByteArray(addressByte.size + 4)
            for (i in addressByte.indices) {
                tronHash[i] = addressByte[i]
            }
            for (i in addressByte.size until tronHash.size) {
                tronHash[i] = secondHash[i - addressByte.size]
            }
            val tronAddress = Base58.encode(tronHash)
            LogUtil.e("AAAA", "波场TRON : address = $tronAddress ;; pri = $pri ;; pub = $pub")
        }.start()
    }

    /**
     * 创建钱包
     */
    private fun buildWallet() {
        val entropy = ByteArray(16)
        SecureRandom().nextBytes(entropy)
//        val mnemonic = MnemonicUtils.generateMnemonic(entropy)
        getViewBinding().tvWords.text = mnemonic
        LogUtil.e("AAAA", "words = $mnemonic")
        val parentPath = cacheDir.absolutePath + File.separator + "eth"
        Thread {
            val file = File(parentPath)
            if (!file.exists()) {
                file.mkdirs()
            }
            val seed = MnemonicUtils.generateSeed(mnemonic, SALT)
            val wallet = WalletUtils.generateBip39WalletFromMnemonic(SALT, mnemonic, file)
            //这个与欧易地址相同
            crash = Bip44WalletUtils.loadBip44Credentials(SALT, mnemonic)
            val pri = Numeric.toHexStringWithPrefixZeroPadded(crash!!.ecKeyPair.privateKey, 64)
            val pub = crash!!.ecKeyPair.publicKey.toString(16)
            val address = crash!!.address
            LogUtil.e("AAAA", "钱包创建完成: ETH系列的 : address = $address ;; pri = $pri ;; pub = $pub")
        }.start()
    }

}