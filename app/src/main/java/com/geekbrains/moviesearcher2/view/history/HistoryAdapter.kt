package com.geekbrains.moviesearcher2.view.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.moviesearcher2.R
import com.geekbrains.moviesearcher2.common.DATE_TIME_PATTERN
import com.geekbrains.moviesearcher2.model.MovieDetailsInt
import com.geekbrains.moviesearcher2.utils.convertMillisToDate
import com.geekbrains.moviesearcher2.utils.convertReleaseDateToYear
import com.geekbrains.moviesearcher2.utils.show

class HistoryAdapter(
    private var onItemViewClickListener: OnItemViewClickListener?
) :
    RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {
    private var data: List<MovieDetailsInt> = arrayListOf()

    fun setData(data: List<MovieDetailsInt>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: MovieDetailsInt) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                with(itemView) {
                    findViewById<TextView>(R.id.hfRecyclerViewMovieTitle).text =
                        String.format(
                            resources.getString(R.string.hfRecyclerViewItemTitle),
                            data.title
                        )
                    findViewById<TextView>(R.id.hfRecyclerViewMovieYear).text =
                        String.format(
                            resources.getString(R.string.hfRecyclerViewItemYear),
                            convertReleaseDateToYear(data.releaseDate).drop(1).dropLast(1)
                        )
                    findViewById<TextView>(R.id.hfRecyclerViewViewTime).text =
                        String.format(
                            resources.getString(R.string.hfRecyclerViewItemViewTime),
                            convertMillisToDate(
                                data.viewTime, DATE_TIME_PATTERN
                            )
                        )
                    if (data.note != "") {
                        findViewById<TextView>(R.id.hfRecyclerViewNote).apply {
                            text = String.format(
                                resources.getString(R.string.hfRecyclerViewItemNote),
                                data.note
                            )
                            show()
                        }
                    }
                    setOnClickListener {
                        onItemViewClickListener?.onItemViewClick(data)
                    }
                }
            }
        }
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(movieDetailsInt: MovieDetailsInt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.fragment_history_recycler_item,
                    parent,
                    false
                ) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}