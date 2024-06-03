package machine
import org.junit.Test
import org.junit.Assert._

class TestFichier {

  // initialisation des objets sous test
  val f = Fichier

  // tests
  @Test
  def test_getCheminAbsolu_fail {
    val cheminAbsolu = ""
    val cheminRelatif = ""

    try {
      f.getCheminAbsolu(cheminRelatif);
      fail();
    } catch {
      case exn: IllegalArgumentException =>
        () // Rattrape uniquement l'exception déclarée, et levée explicitement
      case exn: MatchError =>
        () // Rattrape l'exception matchError levée implicitement
    }
  }

  // tests
  @Test
  def test_extraireDonnees_ligne_cle_valeur {
    for (ligne <- f.extraireDonnees()) {
      assertEquals(
        2,
        ligne.size
      )
    }
  }

}
