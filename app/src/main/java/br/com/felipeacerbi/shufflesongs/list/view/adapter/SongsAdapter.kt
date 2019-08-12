package br.com.felipeacerbi.shufflesongs.list.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.felipeacerbi.shufflesongs.R
import br.com.felipeacerbi.shufflesongs.inflate
import br.com.felipeacerbi.shufflesongs.list.model.Song
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.song_item.view.*

class SongsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val songsList: MutableList<Song> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SongViewHolder(parent.inflate(R.layout.song_item))
    }

    override fun getItemCount(): Int {
        return songsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is SongViewHolder) holder.bind(songsList[position])
    }

    class SongViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(song: Song) {
            with(itemView) {
                tv_song_name.text = song.name
                tv_song_details.text = context.getString(R.string.song_details, song.artist, song.genre)
                Glide.with(context).load(song.picture).into(iv_song_picture)
            }
        }
    }
}