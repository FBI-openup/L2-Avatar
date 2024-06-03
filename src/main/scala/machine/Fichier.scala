package machine

import java.io.File
import scala.io.Source

trait TraitFichier {

  /** Obtient le chemin absolue a partir du chemin relatif
    *
    * @param cheminRelatif
    * @return
    *   le chemin absolue
    */
  def getCheminAbsolu(cheminRelatif: String): String

  /** Obtient les données d'un fichier .csv
    *
    * @param cheminRelatif
    * @param separateur
    * @return
    *   les données du fichier .csv
    */
  def extraireCSV(
      cheminRelatif: String,
      separateur: Char
  ): List[List[String]]

  /** Obtient les données du fichier DonneesInitiales
    *
    * @return
    *   les données du fichier DonneesInitiales
    */
  def extraireDonnees(): List[List[String]]
}

object Fichier extends TraitFichier {
  def getCheminAbsolu(cheminRelatif: String): String = {
    val fichier = new File(cheminRelatif)
    if (fichier.exists()) {
      fichier.getAbsolutePath()
    } else {
      throw new IllegalArgumentException(
        s"Le fichier $cheminRelatif n'existe pas."
      )
    }
  }

  def extraireCSV(
      cheminRelatif: String,
      separateur: Char
  ): List[List[String]] = {
    val fichier = new File(cheminRelatif)
    if (!fichier.exists() || !fichier.isFile()) {
      throw new IllegalArgumentException(
        s"Le fichier $cheminRelatif n'existe pas."
      )
    }
    val lignes = Source.fromFile(fichier).getLines().toList
    lignes.map(ligne => ligne.split(separateur).map(_.trim).toList)
  }

  def extraireDonnees(): List[List[String]] = {
    val cheminRelatif: String = "doc/DonneesInitiales.txt"
    val separateur: Char = ';'
    extraireCSV(cheminRelatif, separateur)
  }
}
