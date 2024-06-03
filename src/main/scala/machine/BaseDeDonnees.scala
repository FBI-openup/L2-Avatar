package machine

trait TraitBaseDeDonnees {

  /** Il extraire les donnees (cf: Fichier.extraireDonnees()) et transformer la
    * liste de liste de String en une map lien un cle composée d'un seul mot.
    * Soit ligne = List[String], Fichier.extraireDonnees() est une liste de
    * ligne avec 2 elements par ligne, une cle et une valeur. Pour chaque liste,
    * le premier element est une phrase cle. Il faut la trier (cf:
    * AnalysePhrase.trier(Parseur.parser(phrase cle))) chaque element (String)
    * obtenu doit être associer à le valeur. A voir si il faut gérer les
    * synonymes
    *
    * @return
    *   une map cle => valeur avec cle un mot et valeur la phrase reponse
    *   associer
    */
  def getMap(): Map[String, String]
}

object BaseDeDonnees extends TraitBaseDeDonnees {
  def getMap(): Map[String, String] = Map("hôtel"-> "", "Mairie" ->  "", "Rennes" -> "","Théâtre" -> "")
}
