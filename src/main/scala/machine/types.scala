package machine

sealed trait Mot
case class Adjectif(adj: String) extends Mot
case class Adverbe(adv: String) extends Mot
case class Conjonction(con: String) extends Mot
case class Determinant(det: String) extends Mot
case class Interjection(int: String) extends Mot
case class Nom(nom: String) extends Mot
case class Nombre(num: String) extends Mot
case class Particule(par: String) extends Mot
case class Preposition(pre: String) extends Mot
case class Pronom(pro: String) extends Mot
case class Verbe(ver: String) extends Mot
case class Autre(mot: String) extends Mot

// Mot a analyser : Adjectif, Adverbe, Nom, Pronom, Verbe, Autre | condition : (.length > 2)
// Mot a supp : Conjonction, Determinant, Interjection, Nombre, Particule, Preposition

sealed trait Reponse
case class Texte(mot: String) extends Reponse
case object Erreur extends Reponse
