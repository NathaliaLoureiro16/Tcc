package com.exemple.nathalia.tcc
import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_receita.*

class ReceitasActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)

        val users = ArrayList<Model_Details>()
        users.add(Model_Details("Kaju katli", "Kaju katli, also known as kaju Katari or kaju barfi, is an Indian dessert similar to a barfi.",R.drawable.kaju))
        users.add(Model_Details("Doughnut", "The doughnut is popular in many countries and prepared in various forms as a sweet snack that can be homemade or purchased in bakeries, supermarkets, food stalls, and franchised specialty outlets",R.drawable.donuts))
        users.add(Model_Details("Panna cotta", "Panna cotta is an Italian dessert of sweetened cream thickened with gelatin and molded. The cream may be aromatized with rum, coffee, vanilla, or other flavorings.",R.drawable.panna_cotta))
        users.add(Model_Details("Rose Cookies", "Rose cooky is a famous South Indian snack made during festivals",R.drawable.rosecookies))
        users.add(Model_Details("Belgian waffle", "In North America, Belgian waffles are a variety of waffle with a lighter batter, larger squares, and deeper pockets than ordinary American waffles",R.drawable.belgianwaffle))

              val obj_adapter = CustomAdapter(users)

        recyclerView.adapter = obj_adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

    }

}
