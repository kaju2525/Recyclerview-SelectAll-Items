package karun.com.recyclearviewselectall

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_circle.view.*

class MyAdapter1(val context: Context, val list: ArrayList<Model1>) : RecyclerView.Adapter<MyAdapter1.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent!!.context).inflate(R.layout.row_circle, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bindFun(list[position])

        holder.image.setOnClickListener({

            list.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, list.size)
        })
    }

    override fun getItemCount(): Int = list?.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image: ImageButton = itemView.findViewById<View>(R.id.btn_remove) as ImageButton

        val context: Context = itemView.context
        fun bindFun(m: Model1) {
            Picasso.with(context).load(m.avatar).into(itemView.img_circle);
            itemView.tv_circle.text = m.name
        }
    }
}