package com.example.nashkodimtrainee.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nashkodimtrainee.R
import com.example.nashkodimtrainee.core.utils.commaSplit
import com.example.nashkodimtrainee.databinding.CryptoViewItemBinding
import com.example.nashkodimtrainee.model.network.entities.MarketsResponceEntity


class CryptoItemAdapter: RecyclerView.Adapter<CryptoItemAdapter.CryptoItemViewHolder>(){

//    Список эл-тов по индексу
    private val items = mutableListOf<MarketsResponceEntity>()
    private lateinit var currency: String
    private var parentContext: Context? = null

    fun replaceItems(newItems: List<MarketsResponceEntity>?){
        items.clear()
        if (newItems != null) {
            items.addAll(newItems)
        }
        notifyDataSetChanged() // Метод адаптера, который обновляет этот адаптер
    }

    fun initCurrency(vsCurrency: String, context: Context?){
        currency = vsCurrency
        parentContext = context
    }

//    Этот метод вызывается столько раз, сколько элементов помещается на экране + 1-2 запасных
//    Пример: Если на экране помещается 6 эл-тов, вызов произойдет 6 раз за всю жизнь адаптера
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CryptoItemViewHolder {
        return CryptoItemViewHolder( // ViewHolder с раздутым макетом внутри
            LayoutInflater.from(parent.context).inflate(R.layout.crypto_view_item, parent, false)
            // Используется LayoutInflater из нашего родительского ViewGroup (recyclerView)
        )
    }

//    Когда нужно отрисовать элемент списка (При первой инициализации либо при scroll) вызывается следующий метод
//    Этот метод берет ViewHolder из метода onCreateViewHolder и вызывается каждый раз когда на экране появляется новый элемент
    override fun onBindViewHolder(holder: CryptoItemViewHolder, position: Int) {
         holder.bind(items[position], currency, parentContext)
    }


    override fun getItemCount(): Int {
        return items.size
    }

//    Этот класс отрисовывает в ячейке recycler'a модель
    class CryptoItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private var binding = CryptoViewItemBinding.bind(itemView)

        fun bind(item: MarketsResponceEntity, currency: String, parentContext: Context?){
            with(binding){
                Glide.with(this.cryptoLabel).load(item.image).into(this.cryptoLabel)
                this.cryptoName.text = item.name
                this.reduction.text = item.symbol
                if (currency == "usd") this.cost.text = parentContext!!
                    .getString(R.string.currency, "$ " + commaSplit(String.format( "%.2f", item.currentPrice)))
                    else this.cost.text = parentContext!!
                    .getString(R.string.currency, "€ " + commaSplit(String.format( "%.2f", item.currentPrice)))
                this.change.text = parentContext.getString(R.string.percent, commaSplit(String.format("%.2f", item.priceChangePercentage24h)))
                if (item.priceChangePercentage24h < 0f) {
                    this.change.setTextColor(ContextCompat.getColor(parentContext, R.color.red))
                } else {
                    this.change.setTextColor(ContextCompat.getColor(parentContext, R.color.green))
                }
            }
        }
    }
}