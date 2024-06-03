package machine

package machine

trait TraitResultat {

  /** Convertie la liste de Reponse en liste de String
    *
    * @param listeReponse
    * @return
    *   list de string
    */
  def reponseToString(listeReponse: List[Reponse]): List[String]
}

object Resultat extends TraitResultat {
  def reponseToString(listeReponse: List[Reponse]): List[String] = ???
}
