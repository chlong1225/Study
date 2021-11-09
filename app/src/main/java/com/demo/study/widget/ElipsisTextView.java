package com.demo.study.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.chl.common.utils.LogUtil;
import com.demo.study.R;

/**
 * create on 11/1/21
 * @author chenglong
 * description : 自定义展开收起的控件
 *
 * 参考类似：https://github.com/Carbs0126/ExpandableTextView
 *
 * 注意事件：
 * 1，展开与收缩icon不支持svg图片
 * 2，使用setContent()代替setText()设置文本
 * 3，展示与收缩的点击事件封装在控件内部处理，禁止设置点击事件
 * 4，展开与收缩的icon填充范围：大小为行高的正方形
 */
public class ElipsisTextView extends AppCompatTextView {

    private static final String TAG = ElipsisTextView.class.getSimpleName();

    //默认最大收缩显示两行
    private static final int MAX_LINES_ON_SHRINK = 2;
    //默认使用...省略过长文本
    private static final String ELLIPSIS_TEXT = "...";

    //显示的原始文本内容
    private String mOriginalText;
    //收缩时裁剪的文本
    private String mCropText;
    //是否需要展开与收缩(文本内容少于最大收缩行能够显示时不需要展开与收缩功能)
    private boolean isNeedShrink = false;
    //收缩显示的最大行数
    private int maxLinesOnShrink = MAX_LINES_ON_SHRINK;
    //是否收缩状态。默认收缩
    private boolean isShrink = true;
    //省略文案
    private String mEllipsisText = ELLIPSIS_TEXT;
    //收缩与展开状态对应的icon
    private int mShinkImageId = R.drawable.uilib_icon_arrow_down;
    private int mExpandImageId = R.drawable.uilib_icon_arrow_up;
    private Bitmap mShinkBitmap;
    private Bitmap mExpandBitmap;
    //展开的icon是否放置在文本的下一行，此时需要重新设置展开与收缩的高度
    private boolean isExpandNextLine = false;
    private int mExpandHeight;

    public ElipsisTextView(@NonNull Context context) {
        this(context, null);
    }

    public ElipsisTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ElipsisTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        init();
    }

    //处理自定义的属性
    private void initAttrs(AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ElipsisTextView);
        maxLinesOnShrink = typedArray.getInt(R.styleable.ElipsisTextView_maxShrinkLines, MAX_LINES_ON_SHRINK);
        mEllipsisText = typedArray.getString(R.styleable.ElipsisTextView_ellipsizeHint);
        if (TextUtils.isEmpty(mEllipsisText)) {
            mEllipsisText = ELLIPSIS_TEXT;
        }
        mShinkImageId = typedArray.getResourceId(R.styleable.ElipsisTextView_shink_icon, R.drawable.uilib_icon_arrow_down);
        mExpandImageId = typedArray.getResourceId(R.styleable.ElipsisTextView_expand_icon, R.drawable.uilib_icon_arrow_up);
    }

    private void init() {
        mOriginalText = getText().toString();
        if (mShinkBitmap == null) {
            mShinkBitmap = BitmapFactory.decodeResource(getResources(), mShinkImageId);
            mExpandBitmap = BitmapFactory.decodeResource(getResources(), mExpandImageId);
        }
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                calculateText();
                showText();
                getViewTreeObserver().removeOnGlobalLayoutListener(this);

            }
        });
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNeedShrink) {
                    if (isShrink) {
                        isShrink = false;
                        showExpand();
                    } else {
                        isShrink = true;
                        showShrink();
                    }
                }
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtil.e(TAG, "onDraw : lineCount = " + getLineCount() + " ;; lineHeight = " + getLineHeight() + " ;; " + getHeight());
        if (isNeedShrink) {
            Rect rect = new Rect(getWidth() - getLineHeight(), getHeight() - getLineHeight(), getWidth(), getHeight());
            if (isShrink) {
                canvas.drawBitmap(mShinkBitmap, null, rect, null);
            } else {
                canvas.drawBitmap(mExpandBitmap, null, rect, null);
            }
        }
    }

    /**
     * 设置文本内容，不要直接使用setText()方法
     * @param content ：
     */
    public void setContent(String content) {
        mOriginalText = content;
        calculateText();
        showText();
    }

    //显示全部文案
    private void showExpand() {
        ViewGroup.LayoutParams params = getLayoutParams();
        if (isExpandNextLine) {
            params.height = mExpandHeight;
            setLayoutParams(params);
        } else {
            if (params.height != ViewGroup.LayoutParams.WRAP_CONTENT) {
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                setLayoutParams(params);
            }
        }
        setText(mOriginalText, BufferType.SPANNABLE);
    }

    //显示收缩的文案
    private void showShrink() {
        ViewGroup.LayoutParams params = getLayoutParams();
        if (params.height != ViewGroup.LayoutParams.WRAP_CONTENT) {
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            setLayoutParams(params);
        }
        setText(mCropText, BufferType.SPANNABLE);
    }

    //计算文本后显示文本
    private void showText() {
        if (isNeedShrink) {
            if (isShrink) {
                showShrink();
            } else {
                showExpand();
            }
        }
    }

    //计算文本，判断是否需要收缩与展开。需要时计算收缩显示的文本，展开时高度
    private void calculateText() {
        //控件修改内容后，getLayout获取的是上一次的layout，必须自己创建
        DynamicLayout layout = new DynamicLayout(mOriginalText, getPaint(), getWidth(), Layout.Alignment.ALIGN_NORMAL, getLayout().getSpacingMultiplier(), getLayout().getSpacingAdd(), false);
        int count = layout.getLineCount();

        double lineHeight1 = Math.ceil(getPaint().getFontMetricsInt().descent - getPaint().getFontMetricsInt().ascent);

        double height1 = Math.ceil(getPaint().getFontMetrics().bottom - getPaint().getFontMetrics().top);

        int lineTop = getLayout().getLineTop(getLayout().getLineCount());

        LogUtil.e(TAG, "calculateText : lineCount = " + getLineCount() + " ;; lineHeight = " + getLineHeight() + " ;; " + count);
        if (count <= maxLinesOnShrink) {
            isNeedShrink = false;
            setText(mOriginalText, BufferType.SPANNABLE);
            return;
        } else {
            isNeedShrink = true;
        }
        //1，先计算收缩文本的内容
        int startIndex = layout.getLineStart(maxLinesOnShrink - 1);
        int endIndex = layout.getLineEnd(maxLinesOnShrink - 1);
        //需要裁剪的宽度
        float cropWidth = StaticLayout.getDesiredWidth(mEllipsisText, getPaint()) + getLineHeight();
        //当前行可以使用的最大宽度
        float maxWidth = getWidth() - cropWidth;
        endIndex -= mEllipsisText.length();
        float compareWidth = StaticLayout.getDesiredWidth(mOriginalText, startIndex, endIndex, getPaint());
        if (compareWidth < maxWidth) {
            //尝试填充更多的文案
            while (compareWidth <= maxWidth) {
                endIndex++;
                compareWidth = StaticLayout.getDesiredWidth(mOriginalText, startIndex, endIndex, getPaint());
            }
            //跳出循环时超出范围，之前可以填充。需要--
            endIndex--;
        } else if (compareWidth > maxWidth) {
            //需要继续裁剪文案
            while (compareWidth > maxWidth) {
                endIndex--;
                compareWidth = StaticLayout.getDesiredWidth(mOriginalText, startIndex, endIndex, getPaint());
            }
        }
        mCropText = mOriginalText.substring(0, endIndex) + mEllipsisText;
        //2，计算展开文本后的高度。当文本右边不够icon显示时，需要放在下一行显示，避免遮住最后的文本
        startIndex = layout.getLineStart(count - 1);
        endIndex = layout.getLineEnd(count - 1);
        //展开后最后一行文本的宽度
        float lastWidth = StaticLayout.getDesiredWidth(mOriginalText, startIndex, endIndex, getPaint());
        maxWidth = getWidth() - getLineHeight();
        if (lastWidth > maxWidth) {
            //icon在最后一行放置不下
            isExpandNextLine = true;
            mExpandHeight = getLineHeight() * (count + 1);
        } else {
            isExpandNextLine = false;
        }
    }
}
