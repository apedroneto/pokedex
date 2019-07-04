package com.example.pokedex

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import com.example.pokedex.config.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {

    var bmp: Bitmap? = null
    var imageBase64: String = "olha a imagem"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCamera.setOnClickListener {

            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 123)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 123) {
            bmp = data?.extras?.get("data") as Bitmap
            imgPokemon.setImageBitmap(bmp)
            convertToBase64()
        }
    }

    fun convertToBase64() {
        doAsync {

            var baos: ByteArrayOutputStream = ByteArrayOutputStream()

            bmp?.compress(Bitmap.CompressFormat.PNG, 100, baos)

            var b = baos.toByteArray()

            var encodedImage: String = Base64.encodeToString(b, Base64.DEFAULT)

            imageBase64 = encodedImage

            var pokeFoto: PokeFoto = PokeFoto()

            pokeFoto.image = imageBase64

            sendImage(pokeFoto)

        }
    }



    private fun sendImage(pokeFoto: PokeFoto) {
        var call = RetrofitInitializer().pokeService().sendImage(pokeFoto)
        call.enqueue(object: Callback<PokeResult> {
            override fun onFailure(call: Call<PokeResult>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<PokeResult>, response: Response<PokeResult>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                textView2.setText(response.body()?.pokemon)

            }

        })
    }
}
