package machine
import org.junit.Test
import org.junit.Assert._
import _root_.machine.machine.Resultat

class TestResultat {

  // initialisation des objets sous test
  val r = Resultat
  val erreur: String = "Je ne comprends pas votre demande"

  // tests
  @Test
  def test_reponseToString_1 {
    val reponses: List[Reponse] = List()
    assertEquals(
      List(erreur),
      r.reponseToString(reponses)
    )
  }

  @Test
  def test_reponseToString_2 {
    val mot1: String = "aaaa"
    val mots: List[String] = List(mot1)
    val reponses: List[Reponse] =
      List(Texte(mot1))
    assertEquals(
      mots,
      r.reponseToString(reponses)
    )
  }

  @Test
  def test_reponseToString_3 {
    val reponses: List[Reponse] = List(Erreur)
    assertEquals(
      List(),
      r.reponseToString(reponses)
    )
  }

  @Test
  def test_reponseToString_4 {
    val mot1: String = "aaaa"
    val mot2: String = "bbbb"
    val mot3: String = "cccc"
    val mot4: String = "dddd"
    val mots: List[String] = List(mot1, mot2, mot3, mot4)
    val reponses: List[Reponse] =
      List(Texte(mot1), Texte(mot2), Texte(mot3), Texte(mot4))
    assertEquals(
      mots,
      r.reponseToString(reponses)
    )
  }

  @Test
  def test_reponseToString_5 {
    val mot1: String = "aaaa"
    val mot2: String = "bbbb"
    val mot3: String = "cccc"
    val mot4: String = "dddd"
    val mots: List[String] = List(erreur, mot1, mot2, mot3, mot4)
    val reponses: List[Reponse] =
      List(Erreur, Texte(mot1), Texte(mot2), Texte(mot3), Texte(mot4))
    assertEquals(
      mots,
      r.reponseToString(reponses)
    )
  }

  @Test
  def test_reponseToString_6 {
    val mot1: String = "aaaa"
    val mot2: String = "bbbb"
    val mot3: String = "cccc"
    val mot4: String = "dddd"
    val mots: List[String] = List(mot1, mot2, mot3, mot4, erreur)
    val reponses: List[Reponse] =
      List(Texte(mot1), Texte(mot2), Texte(mot3), Texte(mot4), Erreur)
    assertEquals(
      mots,
      r.reponseToString(reponses)
    )
  }

  @Test
  def test_reponseToString_7 {
    val mot1: String = "aaaa"
    val mot2: String = "bbbb"
    val mot3: String = "cccc"
    val mot4: String = "dddd"
    val mots: List[String] = List(mot1, mot2, mot3, mot4, erreur)
    val reponses: List[Reponse] =
      List(Erreur, Texte(mot1), Texte(mot2), Texte(mot3), Texte(mot4), Erreur)
    assertEquals(
      mots,
      r.reponseToString(reponses)
    )
  }

  def test_reponseToString_8 {
    val mot1: String = "aaaa"
    val mot2: String = "bbbb"
    val mot3: String = "cccc"
    val mot4: String = "dddd"
    val mots: List[String] = List(mot1, mot2, mot3, mot4, erreur)
    val reponses: List[Reponse] =
      List(
        Erreur,
        Erreur,
        Texte(mot1),
        Texte(mot2),
        Texte(mot3),
        Texte(mot4),
        Erreur
      )
    assertEquals(
      mots,
      r.reponseToString(reponses)
    )
  }

  def test_reponseToString_9 {
    val mot1: String = "aaaa"
    val mot2: String = "bbbb"
    val mot3: String = "cccc"
    val mot4: String = "dddd"
    val mots: List[String] = List(mot1, mot2, mot3, mot4, erreur)
    val reponses: List[Reponse] =
      List(
        Erreur,
        Erreur,
        Texte(mot1),
        Texte(mot2),
        Erreur,
        Texte(mot3),
        Texte(mot4),
        Erreur,
        Erreur
      )
    assertEquals(
      mots,
      r.reponseToString(reponses)
    )
  }

  def test_reponseToString_10 {
    val mots: List[String] = List(erreur)
    val reponses: List[Reponse] =
      List(
        Erreur,
        Erreur,
        Erreur,
        Erreur,
        Erreur,
        Erreur,
        Erreur,
        Erreur
      )
    assertEquals(
      mots,
      r.reponseToString(reponses)
    )
  }
  // TODO : compléter
}
