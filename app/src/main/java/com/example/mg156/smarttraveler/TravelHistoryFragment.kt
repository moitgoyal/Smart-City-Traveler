package com.example.mg156.smarttraveler

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.ShareActionProvider
import android.text.TextUtils
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import java.io.Serializable


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"
private const val ARG_PARAM5 = "param5"

class TravelHistoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null
    private var param4: String? = null
    private var param5: Int? = null
    lateinit var mshareActionProvider: ShareActionProvider

    lateinit var plan: planMetaDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
            param4 = it.getString(ARG_PARAM4)
            param5 = it.getInt(ARG_PARAM5)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val historyView = inflater.inflate(R.layout.fragment_travel_history, container, false)

        val imgView = historyView.findViewById<ImageView>(R.id.history_fragment_image)

        val txtPlanName = historyView.findViewById<TextView>(R.id.history_fragment_plan_name)

        val txtPlanStartTime = historyView.findViewById<TextView>(R.id.history_fragment_plan_start_time)

        val txtPlanEndTime = historyView.findViewById<TextView>(R.id.history_fragment_plan_end_time)

        val txtPlanPreference = historyView.findViewById<TextView>(R.id.history_fragment_plan_preference)

        txtPlanName.setText(param1)
        txtPlanStartTime.setText(param2)
        txtPlanEndTime.setText(param3)
        if (TextUtils.isEmpty(param4)) {
            txtPlanPreference.setText("N/A")
        }else{
            txtPlanPreference.setText(param4)
        }

        if(param5!! % 2 == 0){
            imgView.setImageDrawable(context?.getDrawable(R.drawable.travel_history_1))
        }
        else{
            imgView.setImageDrawable(context?.getDrawable(R.drawable.travel_history_2))
        }

        return historyView
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        if (menu!!.findItem(R.id.share_menu) == null) {
            inflater!!.inflate(R.menu.share_menu_item, menu)
        }
        val menuItem3 = menu.findItem(R.id.share_action_provider)
        if (menuItem3 != null) {
            mshareActionProvider = MenuItemCompat.getActionProvider(menuItem3) as ShareActionProvider
            val intentShare = Intent(Intent.ACTION_SEND)
            intentShare.type = "text/plain"
            intentShare.putExtra(Intent.EXTRA_STREAM,param1)
            mshareActionProvider.setShareIntent(intentShare)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: String, param4: String, param5: Int) =
                TravelHistoryFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                        putString(ARG_PARAM3, param3)
                        putString(ARG_PARAM4, param4)
                        putInt(ARG_PARAM5, param5)
                    }
                }
    }
}
