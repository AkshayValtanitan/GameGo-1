package cit.edu.gamego.helper
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import cit.edu.gamego.R
import cit.edu.gamego.data.Game

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

        val game = listOfGame[position]

        photo.setImageResource(game.photo)
        "${game.name} ${game.date}".also { name.text = it }
        // this is jsut name.text = "${game.name} ${game.date}" but ide wants it to be lke at top



        view.setOnClickListener {
            onClickItem(game)
        }

        view.setOnLongClickListener {
            onLongPress(position)
            true
        }
        return view
    }
}