package com.better.alarm.presenter


import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.better.alarm.R
import com.better.alarm.configuration.globalInject
import com.better.alarm.databinding.RewardLayoutBinding
import com.better.alarm.model.Rewards
import org.koin.ext.getScopeId

class SolveAdapter(
    private val rewards: MutableList<Reward>,
    private val rewardView: TextView
) :RecyclerView.Adapter<SolveAdapter.ScoreViewHolder>() {
    class ScoreViewHolder(val binding: RewardLayoutBinding, val rewardView: TextView, private val rewardorinos: Rewards? = globalInject(Rewards::class.java).value) : RecyclerView.ViewHolder(binding.root){
        //val binding: RewardLayoutBinding = binding
        fun bind(item: Reward){
            binding.tvReward.text = item.name
            binding.tvCost.text = item.cost.toString()

            val tv: TextView = binding.tvReward;
            tv.setOnClickListener{
                tv.paintFlags = tv.paintFlags or STRIKE_THRU_TEXT_FLAG
                val cost: Int = (binding.tvCost.text as String).toInt()
                rewardorinos?.decreaseRewardPoints(cost)
                binding.tvCost.text = ""
                rewardView.text = rewardorinos?.rewardPoints.toString()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val x = LayoutInflater.from(parent.context)
        val y: RewardLayoutBinding = DataBindingUtil.inflate(x, R.layout.reward_layout, parent, false)


        return ScoreViewHolder(
                y,
                rewardView
        )
    }





    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val curReward = rewards[position]

        holder.itemView.apply {

            holder.bind(curReward)
                //.tvReward.text = curReward.name + "   " + curReward.cost + " pts"
        }
    }

    override fun getItemCount(): Int {
        return rewards.size
    }
}