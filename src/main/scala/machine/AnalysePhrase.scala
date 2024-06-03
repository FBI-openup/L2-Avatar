package machine

trait TraitAnalysePhrase {

  /** Obtenir la list des reponses à l'imput phrase
    *
    * @param mots
    * @return
    *   une liste de reponse
    */
  def analyser(phrase: String): List[Reponse]
}

object AnalysePhrase extends TraitAnalysePhrase {

  def analyser(phrase: String): List[Reponse] = {
    var phraseToMots: List[Mot] = Parseur.parser(phrase)
    var motsImportants: List[String] = trier(phraseToMots)
    var mots: List[String] = Tolerance.corriger(motsImportants)
    analyser(mots)
  }

  /** Garder seulement les mots les plus importants en fonction de la categorie
    * lexicale. On garde seulement les Adjectif, Adverbe, Nom, Pronom, Verbe et
    * Autre dont la length > 2
    * @param mots
    * @return
    *   liste de string
    */
  private def trier(mots: List[Mot]): List[String] = ???

  /** Pour chaque mots rechercher la valeur de la cle qui correspond au mot. cf
    * RechercherMotCle
    *
    * @param mots
    * @return
    *   une liste de reponse
    */
  private def analyser(mots: List[String]): List[Reponse] = ???
}
