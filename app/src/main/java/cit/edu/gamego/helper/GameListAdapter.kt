package cit.edu.gamego.helper
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import cit.edu.gamego.GamePreferences
import cit.edu.gamego.R
import cit.edu.gamego.data.Game
import cit.edu.gamego.extensions.setupAndLoad
import com.bumptech.glide.Glide

class GameListAdapter (
    val context: Context,
    val listOfGame: List<Game>,
    val onClickMore: (game: Game) -> Unit,
    val onClickItem: (game: Game) -> Unit,
    val onLongPress: (position: Int) -> Unit
): BaseAdapter(){
    override fun getCount(): Int = listOfGame.size

    override fun getItem(position: Int): Any = listOfGame[position]

    override fun getItemId(position: Int): Long = position.toLong()
    override fun getView(position: Int, contentView: View?, parent: ViewGroup?): View {
        val view = contentView ?: LayoutInflater.from(context).inflate(
            R.layout.item_listview_game,
            parent,
            false
        )

        val photo = view.findViewById<ImageView>(R.id.imageview_photo)
        val name = view.findViewById<TextView>(R.id.textview_name)
        val more = view.findViewById<ImageView>(R.id.iv_more)
        val game = listOfGame[position]
        val heartButton = view.findViewById<ImageView>(R.id.heart)


        //added here
        val imageStr = game.photo?.medium_url ?: "placeholder"

        if (imageStr.startsWith("http")) {
            // Load from URL using Glide
            Glide.with(context)
                .load(imageStr)
                .placeholder(R.drawable.ye)
                .error(R.drawable.ye)
                .into(photo)
        } else {
            // Load from drawable by name
            val resId = context.resources.getIdentifier(imageStr, "drawable", context.packageName)
            if (resId != 0) {
                photo.setImageResource(resId)
            } else {
                photo.setImageResource(R.drawable.ye) // fallback if drawable not found
            }
        }
        "${game.name} ${game.date}".also { name.text = it }
        // this is jsut name.text = "${game.name} ${game.date}" but ide wants it to be lke at top



        more.setOnClickListener{
            onClickMore(game)
        }

        view.setOnClickListener {
            onClickItem(game)
        }

        view.setOnLongClickListener {
            onLongPress(position)
            true
        }

        heartButton.setOnClickListener {
            val favorites = GamePreferences.loadFavorites(context)

            if (favorites.none { it.name == game.name }) {
                favorites.add(game)
                GamePreferences.saveFavorites(context, favorites)
            }
        }
        return view
    }
}