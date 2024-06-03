package machine
import org.junit.Test
import org.junit.Assert._

class TestParseur {

  // initialisation des objets sous test
  val parseur = Parseur

  // tests
  @Test
  def test_spliter_1 {
    val phrase: String =
      ""
    assertEquals(
      List(),
      parseur.spliter(phrase)
    )
  }
  @Test
  def test_spliter_2 {
    val phrase: String = "Je vais à l'école, mais il pleut."
    assertEquals(
      List("je", "vais", "à", "l", "école", "mais", "il", "pleut"),
      parseur.spliter(phrase)
    )
  }

  @Test
  def test_spliter_3 {
    val phrase: String = "2024 :     n "
    println(parseur.spliter(phrase))
    assertEquals(
      List("n"),
      parseur.spliter(phrase)
    )
  }

  @Test
  def test_spliter_4 {
    val phrase: String = "Il est 10h30min et 30 seconde"
    assertEquals(
      List("il", "est", "hmin", "et", "seconde"),
      parseur.spliter(phrase)
    )
  }

  @Test
  def test_spliter_5 {
    val phrase: String = "l'hotel de ville"
    assertEquals(
      List("l", "hotel", "de", "ville"),
      parseur.spliter(phrase)
    )
  }

  @Test
  def test_spliter_6 {
    val phrase: String = "l'hôtel de ville"
    assertEquals(
      List("l", "hôtel", "de", "ville"),
      parseur.spliter(phrase)
    )
  }

  @Test
  def test_parser_1 {
    val phrase: String = ""
    assertEquals(
      List(),
      parseur.parser(phrase)
    )
  }

  @Test
  def test_parser_2 {
    val phrase: String =
      "à de dans avec sur de pour sans vers"
    assertEquals(
      List(
        Preposition("à"),
        Preposition("de"),
        Preposition("dans"),
        Preposition("avec"),
        Preposition("sur"),
        Preposition("de"),
        Preposition("pour"),
        Preposition("sans"),
        Preposition("vers")
      ),
      parseur.parser(phrase)
    )
  }

  @Test
  def test_parser_3 {
    val phrase: String =
      "Je cherche"
    assertEquals(
      List(Pronom("je"), Pronom("cherche")),
      parseur.parser(phrase)
    )
  }

  @Test
  def test_parser_4 {
    val phrase: String =
      "Je cherche la Mairie"
    assertEquals(
      List(Pronom("je"), Pronom("cherche"), Determinant("la"), Nom("mairie")),
      parseur.parser(phrase)
    )
  }

  @Test
  def test_parser_5 {
    val phrase: String =
      "l'hôtel de ville"
    assertEquals(
      List(
        Determinant("l"),
        Nom("hôtel"),
        Preposition("de"),
        Nom("ville")
      ),
      parseur.parser(phrase)
    )
  }

  @Test
  def test_parser_6 {
    val phrase: String =
      "hotl de valle"
    assertEquals(
      List(Pronom("hotl"), Preposition("de"), Adjectif("valle")),
      parseur.parser(phrase)
    )
  }

  @Test
  def test_parser_7 {
    val phrase: String =
      "Bonjour, où se trouve la Mairie?"
    assertEquals(
      List(
        Nom("bonjour"),
        Adverbe("où"),
        Pronom("se"),
        Verbe("trouve"),
        Determinant("la"),
        Nom("mairie")
      ),
      parseur.parser(phrase)
    )
  }

  @Test
  def test_parser_8 {
    val phrase: String =
      "Bonjour, comment tu t'appelles?"
    assertEquals(
      List(
        Nom("bonjour"),
        Adverbe("comment"),
        Pronom("tu"),
        Particule("t"),
        Pronom("appelles")
      ),
      parseur.parser(phrase)
    )
  }
}
