package com.stl.huikao.uilib.widget.wrap

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import com.demo.study.R

/**
 * create on 2022/4/12
 * @author chenglong
 * description : 解析自定义属性
 *
 */
class ParseAttrSetHelp {

    companion object{

        /**
         * 解析TextView对应的shape属性
         */
        fun parseTextView(context: Context, attrs: AttributeSet): GradientDrawable? {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BLTextView)
            val info = AttrsInfo()
            info.solidColor = typedArray.getColor(R.styleable.BLTextView_solid_color, Color.TRANSPARENT)
            info.strokeColor = typedArray.getColor(R.styleable.BLTextView_stroke_color, Color.TRANSPARENT)
            info.strokeWidth = typedArray.getDimensionPixelSize(R.styleable.BLTextView_stroke_width, 0)
            info.allRadius = typedArray.getDimensionPixelSize(R.styleable.BLTextView_all_radius, 0)
            info.topLeftRadius = typedArray.getDimensionPixelSize(R.styleable.BLTextView_top_left_radius, 0)
            info.topRightRadius = typedArray.getDimensionPixelSize(R.styleable.BLTextView_top_right_radius, 0)
            info.bottomLeftRadius = typedArray.getDimensionPixelSize(R.styleable.BLTextView_bottom_left_radius, 0)
            info.bottomRightRadius = typedArray.getDimensionPixelSize(R.styleable.BLTextView_bottom_right_radius, 0)
            typedArray.recycle()
            return buildGradientDrawable(info)
        }

        /**
         * 解析ConstraintLayout对应的shape属性
         */
        fun parseConstraintLayout(context: Context, attrs: AttributeSet): GradientDrawable? {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BLConstraintLayout)
            val info = AttrsInfo()
            info.solidColor = typedArray.getColor(R.styleable.BLConstraintLayout_solid_color, Color.TRANSPARENT)
            info.strokeColor = typedArray.getColor(R.styleable.BLConstraintLayout_stroke_color, Color.TRANSPARENT)
            info.strokeWidth = typedArray.getDimensionPixelSize(R.styleable.BLConstraintLayout_stroke_width, 0)
            info.allRadius = typedArray.getDimensionPixelSize(R.styleable.BLConstraintLayout_all_radius, 0)
            info.topLeftRadius = typedArray.getDimensionPixelSize(R.styleable.BLConstraintLayout_top_left_radius, 0)
            info.topRightRadius = typedArray.getDimensionPixelSize(R.styleable.BLConstraintLayout_top_right_radius, 0)
            info.bottomLeftRadius = typedArray.getDimensionPixelSize(R.styleable.BLConstraintLayout_bottom_left_radius, 0)
            info.bottomRightRadius = typedArray.getDimensionPixelSize(R.styleable.BLConstraintLayout_bottom_right_radius, 0)
            typedArray.recycle()
            return buildGradientDrawable(info)
        }

        /**
         * 根据shape属性值构建Drawable
         */
        private fun buildGradientDrawable(info: AttrsInfo): GradientDrawable? {
            if (info.solidColor == Color.TRANSPARENT && info.strokeColor == Color.TRANSPARENT) {
                return null
            }
            val corners = FloatArray(8)
            //优先使用设置全部圆角的属性
            if (info.allRadius != 0) {
                for (i in 0..7) {
                    corners[i] = info.allRadius.toFloat()
                }
            } else {
                corners[0] = info.topLeftRadius.toFloat()
                corners[1] = info.topLeftRadius.toFloat()
                corners[2] = info.topRightRadius.toFloat()
                corners[3] = info.topRightRadius.toFloat()
                corners[4] = info.bottomRightRadius.toFloat()
                corners[5] = info.bottomRightRadius.toFloat()
                corners[6] = info.bottomLeftRadius.toFloat()
                corners[7] = info.bottomLeftRadius.toFloat()
            }
            val drawable = GradientDrawable().apply {
                shape = info.shape
                cornerRadii = corners
            }
            drawable.setColor(info.solidColor)
            drawable.setStroke(info.strokeWidth, info.strokeColor)
            return drawable
        }
    }
}