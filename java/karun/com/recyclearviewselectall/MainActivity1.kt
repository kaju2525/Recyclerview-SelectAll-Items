package karun.com.recyclearviewselectall

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import karun.com.recyclearviewselectall.select_.MainActivity
import kotlinx.android.synthetic.main.main_activity.*
import org.json.JSONArray


class MainActivity1 : AppCompatActivity() {

    lateinit var myAdapter: MyAdapter1
    lateinit var list: ArrayList<Model1>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        rec2.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        list = ArrayList<Model1>()

        val datas=intent.getStringExtra("key")
        Log.d("TAGS ","Response: "+datas)
        if(datas!=null){
            val array=JSONArray(datas)
            for(i in 0 until array.length()){
                val obj=array.getJSONObject(i)
                list.add(Model1(obj.getInt("id"), obj.getString("name"),obj.getString("avatar")))
            }
        }

        btn.setOnClickListener({
            var sb=StringBuilder()
            for(m: Model1 in list){
                sb.append("${m.ids}~")
            }
            try {
                sb.deleteCharAt(sb.lastIndex)
                    Log.d("TAGS ","Response StringBuilder: "+sb.toString())
                startActivity(Intent(this@MainActivity1, MainActivity::class.java).putExtra("key",sb.toString()))
            } catch (e: Exception) {
                startActivity(Intent(this@MainActivity1, MainActivity::class.java).putExtra("key",sb.toString()))
            }


        })


        myAdapter = MyAdapter1(this@MainActivity1, list)
        rec2.adapter = myAdapter
        myAdapter.notifyDataSetChanged()

    }
}