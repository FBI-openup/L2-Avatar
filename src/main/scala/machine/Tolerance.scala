package machine

trait TraitTolerance {

  /** Corrige les mots de la liste avec une marge d'erreur relative si ce n'est
    * pas possible, on ne le change pas. Utiliser corrigerMot
    *
    * @param liste
    * @return
    *   une liste de mot corrigé
    */
  def corriger(liste: List[String]): List[String]

  /** Corrige le mot avec une marge d'erreur sinon ""
    *
    * @param String
    */
  def corrigerMot(mot: String): String
}

object Tolerance extends TraitTolerance {

  val dicKeys = BaseDeDonnees.getMap().keys.toList

  def levenshteinDistance(s1: String, s2: String): Int = {
    if (s1.isEmpty) s2.length
    else if (s2.isEmpty) s1.length
    else {
      val cost =
        if (accents(s1.head) == accents(s2.head)) 0
        else 1 // ignorer les accents avec accents()
      val min1 = levenshteinDistance(s1.tail, s2) + 1
      val min2 = levenshteinDistance(s1, s2.tail) + 1
      val min3 = levenshteinDistance(s1.tail, s2.tail) + cost
      math.min(math.min(min1, min2), min3)
    }
  }
  def corriger(liste: List[String]): List[String] = {
    liste.map(corrigerMot(_))
  }

  def corrigerMot(mot: String): String = {
    corrigerMotRec(mot, dicKeys)
  }

  def corrigerMotRec(mot: String, dico: List[String]): String = {
    println("mot: " + mot + "dico:" + dico);
    dico match {
      case Nil => mot
      case cle :: reste => {
        println("Leven: " + levenshteinDistance(mot, cle) + ", " + cle);
        levenshteinDistance(mot, cle) match {

          case 0 => mot
          case 1 => cle
          case _ => corrigerMotRec(mot, reste)
        }
      }
    }
  }

  def accents(lettre: Char): Char = {
    lettre match {
      case 'é' | 'è' | 'ê' | 'ë' => 'e'
      case 'à' | 'â' | 'ä'       => 'a'
      case 'ù' | 'û' | 'ü'       => 'u'
      case 'ô' | 'ö'             => 'o'
      case 'ï' | 'î'             => 'i'
      case 'ç'                   => 'c'
      case _                     => lettre
    }
  }
}
