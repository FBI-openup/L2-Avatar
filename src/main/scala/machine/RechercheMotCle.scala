package machine

trait TraitRechercheMotCle {

  /** Pour chaque mot renvoi la valeur de la cle qui correspond le mieux au mot.
    * Attention que ce passe-il s'il y a plusieurs cles = mot ?
    *
    * @param mot
    * @return
    */
  def reponse(mot: String): Reponse
}

object RechercheMotCle extends TraitRechercheMotCle {
  def reponse(mot: String): Reponse = reponse(mot, BaseDeDonnees.getMap())

  private def reponse(
      mot: String,
      baseDeDonnees: Map[String, String]
  ): Reponse = ???

}
