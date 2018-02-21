package com.logicdojo.daytradealert.ui

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.logicdojo.daytradealert.R
import de.codecrafters.tableview.TableHeaderAdapter


/**
 * Adapter for crypto table header
 */
class CryptoTableHeaderAdapter(context: Context, columnCount: Int) : TableHeaderAdapter(context, columnCount) {
    private var headers: Array<String> = arrayOf("Name", "Trend", "Price", "Change")
    private var paddingLeft = 20
    private var paddingTop = 30
    private var paddingRight = 20
    private var paddingBottom = 30
    private var textSize = 18
    private var typeface = Typeface.BOLD

    override fun getHeaderView(columnIndex: Int, parentView: ViewGroup?): View {
        val textView = TextView(context)

        if (columnIndex < headers.size) {
            textView.text = headers[columnIndex]
        }

        textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
        textView.setTypeface(textView.typeface, typeface)
        textView.textSize = textSize.toFloat()
        textView.setTextColor(ContextCompat.getColor(context, R.color.white))
        textView.setSingleLine()
        textView.ellipsize = TextUtils.TruncateAt.END

        return textView
    }
}