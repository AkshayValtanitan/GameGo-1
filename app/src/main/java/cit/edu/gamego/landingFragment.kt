package cit.edu.gamego

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SearchView // Use this for the correct SearchView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cit.edu.gamego.data.Game
import cit.edu.gamego.helper.GameListAdapter
import cit.edu.gamego.helper.GameRecyclerViewAdapterwGlide

import cit.edu.gamego.data.ApiClient
import cit.edu.gamego.data.GameApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import cit.edu.gamego.data.Image
import cit.edu.gamego.helper.GameRecyclerViewAdapter

class landingFragment : Fragment() {
    private lateinit var listOfGame: MutableList<Game>
    private lateinit var filteredList: MutableList<Game>
    private lateinit var arrayAdapter: GameListAdapter
    private lateinit var searchView: SearchView
    private lateinit var listView: ListView
    private lateinit var gameAdapter: GameRecyclerViewAdapterwGlide
    private val listOfRandomGames = mutableListOf<Game>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.activity_landing, container, false)

        searchView = view.findViewById(R.id.searchView)
        listView = view.findViewById(R.id.listview)


        // temp
        val bmwTrailer = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/pnSsgRJmsCc?si=Fy9aZVKwThO7lKAi\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>".trimIndent()
        val helldiversTrailer = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/UC5EpJR0GBQ?si=1IogxXO2cIXA1Mw5\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>".trimIndent()
        val mhwTrailer = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/OotQrKEqe94?si=ZJMQ0Ipel03V7Ouq\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>".trimIndent()
        val kanyeeee = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/X3d5rT7FGLE?si=Fekc_iv8eCUh2DHp\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>".trimIndent()
        val dota2Trailer = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/-cSFPIwMEq4?si=snIX76ATWr7M80fI\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>".trimIndent()
        val lolTrailer = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/RprbAMOPsH0?si=RCu5hNf6GQoDNvjg\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>".trimIndent()
        val cs2Trailer = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/nSE38xjMLqE?si=NrrEFt0Up-TpVYpJ\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>".trimIndent()
        val valoTrailer = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/e_E9W2vsRbQ?si=0IaLcsue6tIicRol\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>".trimIndent()
        val gowTrailer = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/hfJ4Km46A-0?si=baY8Yfl9Zer1BSCn\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>".trimIndent()
        val eldenRingTrailer = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/AKXiKBnzpBQ?si=zBuJ8VqG7Y5gjWOU\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>".trimIndent()
        //////////////////////// RECYCLER VIEW

        val listOfGame2 = mutableListOf(
            Game("YE Quest", "2030", "1.1", Image(R.drawable.ye.toString()), kanyeeee, "The visionary's journey through a surreal rap universe.", false, "game_yequest", listOf("PS5", "PC"), "Yeezy Interactive", listOf("Adventure", "Rhythm"), listOf("Hip-Hop", "Satire"), listOf("YE Series"),listOf("Yeezy Productions"), "YQ"),
            Game("Helldivers 2", "2022", "8.2", Image(R.drawable.helldivers.toString()), helldiversTrailer, "A chaotic co-op shooter fighting for liberty across galaxies.", false, "game_helldivers2", listOf("PS5", "PC"), "Arrowhead Game Studios", listOf("Shooter", "Co-op"), listOf("Sci-fi", "Military"),  listOf("Helldivers"), listOf("PlayStation Studios"),"HD2"),
            Game("Black Myth Wukong", "2024", "9.3", Image(R.drawable.bmw.toString()), bmwTrailer, "An epic retelling of the legendary Monkey King's story.", false, "game_blackmythwukong", listOf("PS5", "PC"), "Game Science", listOf("Action RPG"), listOf("Mythology", "Fantasy"),  listOf("Black Myth"),listOf("Game Science"), "BMW"),
            Game("Monster Hunter: World", "2018", "8.4", Image(R.drawable.mhw.toString()), mhwTrailer, "Hunt massive monsters in a living, breathing ecosystem.", false, "game_mhw", listOf("PS4", "Xbox One", "PC"), "Capcom", listOf("Action", "RPG"), listOf("Fantasy", "Exploration"), listOf("Monster Hunter"),  listOf("Capcom"),"MHW"),
            Game("DOTA 2", "2011", "8.8", Image(R.drawable.dota.toString()), dota2Trailer, "Valve’s legendary competitive MOBA title.", false, "game_dota2", listOf("PC"), "Valve", listOf("MOBA"), listOf("Fantasy"),  listOf("DOTA"), listOf("Valve"),"Defense of the Ancients 2"),
            Game("League of Legends", "2012", "0.0", Image(R.drawable.lol.toString()), lolTrailer, "The world’s biggest MOBA with global competitive scenes.", false, "game_lol", listOf("PC"), "Riot Games", listOf("MOBA"), listOf("Fantasy"),  listOf("League Universe"),listOf("Riot Games"), "LoL"),
            Game("Counter Strike 2", "2023", "6.6", Image(R.drawable.cs2.toString()), cs2Trailer, "The next evolution of the iconic tactical shooter.", false, "game_cs2", listOf("PC"), "Valve", listOf("Shooter", "Tactical"), listOf("Modern Warfare"),  listOf("Counter-Strike"), listOf("Valve"),"CS2"),
            Game("God of War: Ragnarok", "2022", "9.9", Image(R.drawable.gowrag.toString()), gowTrailer, "The epic Norse saga of Kratos and Atreus continues.", false, "game_gowrag", listOf("PS4", "PS5"), "Santa Monica Studio", listOf("Action", "Adventure"), listOf("Mythology"),  listOf("God of War"),listOf("Sony Interactive Entertainment"), "GOW: Ragnarok"),
            Game("Valorant", "2020", "5.5", Image(R.drawable.valo.toString()), valoTrailer, "A 5v5 tactical shooter with unique agent abilities.", false, "game_valorant", listOf("PC"), "Riot Games", listOf("Shooter", "Tactical"), listOf("Futuristic"),  listOf("Valorant"),listOf("Riot Games"), "Valo"),
            Game("Elden Ring", "2018", "10.0", Image(R.drawable.eldenring.toString()), eldenRingTrailer, "A dark fantasy masterpiece from FromSoftware and George R.R. Martin.", false, "game_eldenring", listOf("PS4", "PS5", "Xbox", "PC"), "FromSoftware", listOf("RPG", "Soulslike"), listOf("Dark Fantasy"),  listOf("Elden Ring"),listOf("Bandai Namco"), "ER")
        )


        val recyclerView =view.findViewById<RecyclerView>(R.id.recyclerview)
        val rgRecyclerView = view.findViewById<RecyclerView>(R.id.randomGamesRecyclerView)
        rgRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        gameAdapter = GameRecyclerViewAdapterwGlide(
            requireContext(),
            listOfRandomGames,
            onClick ={game->
                moreWGlide(game)
            }
        )
        rgRecyclerView.adapter = gameAdapter
        recyclerView.adapter = GameRecyclerViewAdapter(
            requireContext(),
            listOfGame2,
            onClick ={game->
                more(game)
            }
        )

        fetchGames()
        //////////////////////////////////////LIST VIEW BELOW
        // Initially hide listView
        listView.visibility = View.GONE

        // Sample Data
        val listOfGame = mutableListOf(
            Game("YE Quest", "2030", "1.1", Image(R.drawable.ye.toString()), kanyeeee, "The visionary's journey through a surreal rap universe.", false, "game_yequest", listOf("PS5", "PC"), "Yeezy Interactive", listOf("Adventure", "Rhythm"), listOf("Hip-Hop", "Satire"), listOf("YE Series"),listOf("Yeezy Productions"), "YQ"),
            Game("Helldivers 2", "2022", "8.2", Image(R.drawable.helldivers.toString()), helldiversTrailer, "A chaotic co-op shooter fighting for liberty across galaxies.", false, "game_helldivers2", listOf("PS5", "PC"), "Arrowhead Game Studios", listOf("Shooter", "Co-op"), listOf("Sci-fi", "Military"),  listOf("Helldivers"), listOf("PlayStation Studios"),"HD2"),
            Game("Black Myth Wukong", "2024", "9.3", Image(R.drawable.bmw.toString()), bmwTrailer, "An epic retelling of the legendary Monkey King's story.", false, "game_blackmythwukong", listOf("PS5", "PC"), "Game Science", listOf("Action RPG"), listOf("Mythology", "Fantasy"),  listOf("Black Myth"),listOf("Game Science"), "BMW"),
            Game("Monster Hunter: World", "2018", "8.4", Image(R.drawable.mhw.toString()), mhwTrailer, "Hunt massive monsters in a living, breathing ecosystem.", false, "game_mhw", listOf("PS4", "Xbox One", "PC"), "Capcom", listOf("Action", "RPG"), listOf("Fantasy", "Exploration"), listOf("Monster Hunter"),  listOf("Capcom"),"MHW"),
            Game("DOTA 2", "2011", "8.8", Image(R.drawable.dota.toString()), dota2Trailer, "Valve’s legendary competitive MOBA title.", false, "game_dota2", listOf("PC"), "Valve", listOf("MOBA"), listOf("Fantasy"),  listOf("DOTA"), listOf("Valve"),"Defense of the Ancients 2"),
            Game("League of Legends", "2012", "0.0", Image(R.drawable.lol.toString()), lolTrailer, "The world’s biggest MOBA with global competitive scenes.", false, "game_lol", listOf("PC"), "Riot Games", listOf("MOBA"), listOf("Fantasy"),  listOf("League Universe"),listOf("Riot Games"), "LoL"),
            Game("Counter Strike 2", "2023", "6.6", Image(R.drawable.cs2.toString()), cs2Trailer, "The next evolution of the iconic tactical shooter.", false, "game_cs2", listOf("PC"), "Valve", listOf("Shooter", "Tactical"), listOf("Modern Warfare"),  listOf("Counter-Strike"), listOf("Valve"),"CS2"),
            Game("God of War: Ragnarok", "2022", "9.9", Image(R.drawable.gowrag.toString()), gowTrailer, "The epic Norse saga of Kratos and Atreus continues.", false, "game_gowrag", listOf("PS4", "PS5"), "Santa Monica Studio", listOf("Action", "Adventure"), listOf("Mythology"),  listOf("God of War"),listOf("Sony Interactive Entertainment"), "GOW: Ragnarok"),
            Game("Valorant", "2020", "5.5", Image(R.drawable.valo.toString()), valoTrailer, "A 5v5 tactical shooter with unique agent abilities.", false, "game_valorant", listOf("PC"), "Riot Games", listOf("Shooter", "Tactical"), listOf("Futuristic"),  listOf("Valorant"),listOf("Riot Games"), "Valo"),
            Game("Elden Ring", "2018", "10.0", Image(R.drawable.eldenring.toString()), eldenRingTrailer, "A dark fantasy masterpiece from FromSoftware and George R.R. Martin.", false, "game_eldenring", listOf("PS4", "PS5", "Xbox", "PC"), "FromSoftware", listOf("RPG", "Soulslike"), listOf("Dark Fantasy"),  listOf("Elden Ring"),listOf("Bandai Namco"), "ER")
        )


        filteredList = listOfGame.toMutableList()

        // Initialize Adapter
        arrayAdapter = GameListAdapter(
            requireContext(),
            filteredList,
            onClickMore = { game -> more(game) },
            onClickItem = { game ->
                // Do not add the same game again, just highlight/select it
                abc(game)
            },
            onLongPress = { position -> showDeleteDialog(position) }
        )

        listView.adapter = arrayAdapter


        ////////////////////////////SEARCH VIWE BELOW
        searchView.setOnClickListener {
            searchView.isIconified = false
        }

        // Show ListView when clicking SearchView
        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            listView.visibility = if (hasFocus) View.VISIBLE else View.GONE
        }

        // SearchView Listener
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterList(query)
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                if (newText.isNullOrEmpty()) {
                    searchView.isIconified = true // Collapse SearchView when cleared
                    listView.visibility = View.GONE
                }
                return true
            }
        })
        searchView.setOnCloseListener {
            listView.visibility = View.GONE // Hide ListView
            false
        }

        return view;
    }
    private fun fetchGames() {
        val apiKey = BuildConfig.Giant_Bomb_API_KEY

        // Make the API call
        val call = ApiClient.api.getGames(apiKey)

        // Asynchronously handle the response
        call.enqueue(object : Callback<GameApiResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<GameApiResponse>,
                response: Response<GameApiResponse>
            ) {
                if (response.isSuccessful) {
                    // Log the games list
                    val games = response.body()?.results
                    Log.d("API", "Fetched ${games?.size} games")
                    if(games != null){
                        listOfRandomGames.clear()
                        listOfRandomGames.addAll(games.map{ game->
                            Game(
                                guid = game.guid,
                                name = game.name ?: "Unknown",
                                date = game.original_release_date,
                                rating = "1.1",
                                photo = game.image,
                                gameTrailer = """
        <iframe width="100%" height="100%" src="https://www.youtube.com/embed/pnSsgRJmsCc?si=Fy9aZVKwThO7lKAi" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
    """.trimIndent(),
                                description = "",
                                isLiked = false,
                                platform = emptyList(),
                                developer = "",
                                genre = emptyList(),
                                theme = emptyList(),
                                franchise = emptyList(),
                                publishers = emptyList(),
                                alias = ""
                            )
                        })
                        gameAdapter.notifyDataSetChanged()
                    }
                } else {
                    Log.e("API", "Error: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<GameApiResponse>, t: Throwable) {
                Log.e("API", "Failed: ${t.message}")
            }
        })
    }
    private fun filterList(query: String?) {
        filteredList.clear()
        if (query.isNullOrEmpty()) {
            filteredList.addAll(listOfGame) // Show all if search is empty
        } else {
            val searchQuery = query.lowercase()
            filteredList.addAll(listOfGame.filter {
                //added things here
                it.name.lowercase().contains(searchQuery) || it.date?.contains(searchQuery) ?: false
            })
        }
        arrayAdapter.notifyDataSetChanged()

        // Show listView only when there are resultsa
        listView.visibility = if (filteredList.isNotEmpty()) View.VISIBLE else View.GONE
    }

    private fun more(game: Game) {
        startActivity(
            Intent(requireContext(), reviewPageActivity::class.java).apply {
                putExtra("title", game.name)
                putExtra("date",game.date)
                putExtra("imageRes", game.photo?.medium_url)
                putExtra("trailer", game.gameTrailer)
                putExtra("ratings", game.rating)
                putExtra("desc", game.description)
                putExtra("isLiked", game.isLiked)
                putExtra("platform", game.platform?.joinToString(", ") ?: "N/A")
                putExtra("developer", game.developer ?: "N/A")
                putExtra("publishers", game.publishers?.joinToString(", ") ?: "N/A")
                putExtra("genre", game.genre?.joinToString(", ") ?: "N/A")
                putExtra("theme", game.theme?.joinToString(", ") ?: "N/A")
                putExtra("franchise", game.franchise?.joinToString(", ") ?: "N/A")
                putExtra("alias", game.alias ?: "N/A")
            }
        )
    }


    private fun moreWGlide(game: Game) {
        startActivity(
            Intent(requireContext(), reviewPageActivity::class.java).apply {
                putExtra("title", game.name)
                putExtra("date",game.date)
                putExtra("imageReswGlide", game.photo?.medium_url)
                putExtra("trailer", game.gameTrailer)
                putExtra("ratings", game.rating)
                putExtra("guid", game.guid)
                putExtra("desc", game.description)
                putExtra("isLiked", game.isLiked)
                putExtra("platform", game.platform?.joinToString(", ") ?: "N/A")
                putExtra("developer", game.developer ?: "N/A")
                putExtra("genre", game.genre?.joinToString(", ") ?: "N/A")
                putExtra("theme", game.theme?.joinToString(", ") ?: "N/A")
                putExtra("franchise", game.franchise?.joinToString(", ") ?: "N/A")
                putExtra("publishers", game.publishers?.joinToString(", ") ?: "N/A")
                putExtra("alias", game.alias ?: "N/A")
            }
        )
    }


    private fun abc(game: Game){

    }

    private fun showDeleteDialog(position: Int) {
        // Implement delete logic
    }
}