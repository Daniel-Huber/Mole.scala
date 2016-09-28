{import java.awt.Color

object Graphics{
	import cslib.window.SimpleWindow
  val width = 30
  val height = 50
  val blocksize = 10
  val w = new SimpleWindow(width * blocksize, height * blocksize, "Digging")

	def waitForKey(): Char = {
		do {
			w.waitForEvent()
		} while(w.getEventType() != SimpleWindow.KEY_EVENT)
		w.getKey
		}
	}

  def block(x: Int, y: Int, color: Color = Color.black): Unit = {
    w.setLineColor(color)
    val left = x * blocksize
    val right = left + blocksize - 1
    val top = y * blocksize
    val bottom = top + blocksize - 1

    for (row <- top to bottom) {
      w.moveTo(left, row)
      w.lineTo(right, row)
    }
  }

	def rectangle(x: Int, y: Int, width: Int, height: Int, color: Color)
		//x o y = koordinaterna för rektangelns övre vänstra hörn. width, height = bredd resp höjd. Color = färg
		for (yy <- y until (y + height)){
			for (xx <- until (x + width)){
				block(xx, yy, color)
			}
		}

}
object Colors{	//Bestämmer färg för mullvaden, jorden och tunneln.
  val mole = new Color(51, 51, 0)
  val soil = new Color(153, 102, 51)
  val tunnel = new Color(204, 153, 102)
}
} //import av java.awt.Color slutparentes

  //def square(x: Int, y: Int): Unit = { //Gör en kvadrat med l=10 vid x, y coordinater
    //  w.moveTo(x, y)
    //  w.lineTo(x + 10, y)
      //w.lineTo(x + 10, y + 10)
      //w.lineTo(x, y + 10)
      //w.lineTo(x, y)
//  }


object Mole {
  def main(args: Array[String]): Unit = {
    Mole.drawWorld()
		Mole.dig()
  }

	def dig(): Unit = {
		var x = Graphics.width / 2
		var y = Graphics.height / 2
		while (true) {
			Graphics.block(x, y, Colors.mole) //mullvadens startPos
			val key = Graphics.waitForKey()
			if (key == 'w'){ //mullvaden går uppåt
				x = x
				y = y + height
				Graphics.block(x, y, Color.soil)
			}
			else if (key == 'a') {//mullvaden går vänster
				x = x - width
				y = y
				Graphics.block(x, y, Color.soil)
			}
			else if (key == 's') {//mullvaden går neråt
				x = x
				y = y - height
				Graphics.block(x, y, Color.soil)
			}
			else if (key == 'd') {//mullvaden går höger
				x = x + width
				y = y
				Graphics.block(x, y, Color.soil)
			}
		}
	}
	def drawWorld(): Unit = { //ritar ut mullvadens värld med rektanglar
		startX = 0
		startY = 0
		val width = 30
		val height = 50
		Graphics.rectangle(startX, startY, width, height, Colors.soil)
	}
}
