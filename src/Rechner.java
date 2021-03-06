
public class Rechner {

	public static void main(String[] args) {
		// Vor Klammern muss auch bei Multiplikation zwingend ein Rechenzeichen.
		// Rechner.main() dient nur dem Debug, von aussen wird direkt auf
		// Rechner.rechnen() zugegriffen.
		// Auch die Konsolenausgaben dienen haupts?chlich dem Debug, nicht den
		// Grundfunktionen des Rechners, aber nicht alle durch falsche Angaben m?glichen
		// Fehler werden abgefangen. Beispielsweise k?nnen falsche Klammereingaben zu
		// Fehlern f?hren die nicht von klammergleichgewicht() abgefangen werden.
		String rechnung = "(-46.33-78.09/48.55+-67.5*-84.37*80.52+-15.25*(-47.62+-20.2/-87.44/87.13+8.22+-3.12/1.43+-37.08+-5.66/-89.18*22.45*-53.2)/-23.05-54.62--91.56/(68.34*-33.93+-40.92)/14.09*(-8.63+75.73*81.89*-86.87*(58.04--62.07)))"; // (-46.33-78.09/48.55+-67.5*-84.37*80.52+-15.25*(-47.62+-20.2/-87.44/87.13+8.22+-3.12/1.43+-37.08+-5.66/-89.18*22.45*-53.2)/-23.05-54.62--91.56/(68.34*-33.93+-40.92)/14.09*(-8.63+75.73*81.89*-86.87*(58.04--62.07)))
		String ergebnis;

		ergebnis = rechnen(rechnung);
		System.out.println("endErgebnis: " + ergebnis);
	}

	public static String version() {
		String version = "of better Klammer() handling";
		return version;
	}
	
	// Rechner.rechnen nimmt die Rechnung an und gibt das Ergebnis der Rechnung
	// zur?ck.
	public static String rechnen(String rechnung) {
		String zeichen = "+";
		System.out.println("Rechnung " + rechnung);
		// Erster Schritt ist das ?berpr?fen auf validit?t der Klammern.
		String kgg = klammergleichgewicht(rechnung);
		if (kgg.equals("unterschiedlich")) {
			rechnung = "?berpr?fe die Klammersetzung.";
		}
		// Wenn Klammern vorhanden und valide sind, m?ssen die zuerst gerechnet werden,
		// also wird hier der Inhalt des Klammerpaares, das an der Reihe ist,
		// extrahiert.
		else if (kgg.equals("ja")) {
			rechnung = formatierungshilfe(rechnung);
			while (kgg.equals("ja")) {
				String zwischenErgebnis;
				String darin;

				darin = rechnung.substring(rechnung.lastIndexOf("(") + 1,
						rechnung.indexOf(")", rechnung.lastIndexOf("(")));

				System.out.println("while darin " + darin);
				// Der Inhalt des Klammerpaares wird ausgerechnet.
				zwischenErgebnis = (strichrechnung(punktrechnung(darin)));
				System.out.println("zwischenErgebnis " + zwischenErgebnis);
				// Die Rechnung wird mit dem Ergebnis aus der Klammer aktualisiert.
				System.out.println("neu rechnung teil1 " + rechnung.substring(0, rechnung.lastIndexOf("(")));
				System.out.println("neu rechnung teil2 " + zwischenErgebnis);
				System.out.println("neu rechnung teil3 "
						+ rechnung.substring(rechnung.indexOf(")", rechnung.lastIndexOf("(")) + 1));

				rechnung = rechnung.substring(0, rechnung.lastIndexOf("(")) + zwischenErgebnis
						+ rechnung.substring(rechnung.indexOf(")", rechnung.lastIndexOf("(")) + 1);
				rechnung = formatierungshilfe(rechnung);
				System.out.println("neu rechnung am ende der while kgg= ja " + rechnung);

				// Der Status von Klammern in der Rechnung wird aktualisiert.
				kgg = klammergleichgewicht(rechnung);
				System.out.println("kgg " + kgg);
			}
			// nachdem keine Klammern mehr vorhanden sind, wird au?erhalb auch noch
			// verrechnet.
			rechnung = formatierungshilfe(rechnung);
			System.out.println("rechnung vor punktrechnung " + rechnung);
			rechnung = punktrechnung(rechnung);
			System.out.println("rechnung vor strichrechnung " + rechnung);
			rechnung = strichrechnung(rechnung);
			// Wenn keine Klammern vorhanden sind, wird sofort ausgerechnet.
		} else if (kgg.equals("keine")) {
			rechnung = formatierungshilfe(rechnung);
			rechnung = punktrechnung(rechnung);
			rechnung = strichrechnung(rechnung);
		}
		// Am Ende wird das Ergebnis der Rechnung zur?ckgegeben.
		if (rechnung.charAt(0) == zeichen.charAt(0)) {
			rechnung = rechnung.substring(1);
		}
		return rechnung;
	}

	// Rechner.klammergleichgewicht verlangt den RechnungsString und wertet diesen
	// auf
	// ()Klammern aus. Gibt "keine" wieder, wenn keine ()Klammern im String sind,
	// gibt "ja" wieder, wenn ()Klammern in gleicher Anzahl>0 vorhanden sind, gibt
	// "unterschiedlich" wieder, wenn die Anzahl der "(" im String und die Anzahl
	// der ")" im String nicht ?bereinstimmen.
	public static String klammergleichgewicht(String r) {
		// Der Inhalt dieses Strings wird f?r die gleich folgende Suche benutzt.
		String klammern = "()";
		// Diese ints werden f?r das Zaehlen der KlammerAufs und KlammerZus benutzt.
		int kla = 0;
		int klz = 0;
		// Beide KlammerArten werden gezaehlt.
		for (int i = 0; i < r.length(); i++) {
			if (r.charAt(i) == klammern.charAt(0)) {
				kla = kla + 1;
			} else if (r.charAt(i) == klammern.charAt(1)) {
				klz = klz + 1;
			}
		}
		// Die Anzahl der KlammerArten wird verglichen und das Ergebnis des Vergleichs
		// zur?ckgegeben.
		if (kla != klz) {
			return ("unterschiedlich");
		} else if (kla == 0) {
			return ("keine");
		} else {
			return ("ja");
		}
	}

	// Rechner.strichrechnung verlangt einen String und durchsucht diesen nach + und
	// - Operatoren. Anschlie?end wird ein Substring von Beginn des Strings bis zum
	// ersten gefundenen Operator in einen Double gesetzt und ein Substring von nach
	// dem Operator bis zum n?chsten Operator oder dem Stringende ebenfalls in ein
	// weiteres Double gesetzt. Dann wird die Rechnung Double + bzw - Double
	// ausgef?hrt und im String die beiden Doubles und der Operator durch das
	// Ergebnis ersetzt. Wird wiederholt bis keine + und - Operatoren mehr im String
	// gefunden werden. Gibt den ?berarbeiteten String zur?ck.
	public static String strichrechnung(String r) {
		System.out.println("STRICHRECHNUNG auf r " + r);
		// Der Inhalt dieses Strings wird f?r die gleich folgende Suche benutzt.
		String striche = "+-*/E";
		// Diese Variablen werden benutzt f?r...
		// die Position im String vor der (Zwischen-)/Rechnung.
		int vorp = 0;
		// die Zahl vor dem Rechenoperator.
		double vor = 0;
		// die Position im String nach der (Zwischen-)/Rechnung.
		int nachp = r.length();
		// die Zahl nach dem Rechenoperator.
		double nach = 0;
		// das (Zwischen-)/Ergebnis
		double ergebnis = 0;
		// die Position im String des Operators der (Zwischen-)/Rechnung.
		int op = -2;
		// den Rechenoperator
		String o;

		r = formatierungshilfe(r);
		// Es wird nach dem Rechenoperator gesucht.
		if (Math.min(r.indexOf("-", 1), r.indexOf("+", 1)) > 0) {
			System.out.println("min ");
			op = Math.min(r.indexOf("-", 1), r.indexOf("+", 1));
			System.out.println("op " + op);
		} else {
			System.out.println("max ");
			op = Math.max(r.indexOf("-", 1), r.indexOf("+", 1));
			System.out.println("op " + op);
			if (op == -1) {
				return r;
			}
		}
		if (r.charAt(op - 1) == striche.charAt(4)) {
			System.out.println("-E als ersten OP");
			if (Math.min(r.indexOf("-", op + 1), r.indexOf("+", op + 1)) > 0) {
				System.out.println("min 2 ");
				op = Math.min(r.indexOf("-", op + 1), r.indexOf("+", op + 1));
				System.out.println("op " + op);
			} else {
				System.out.println("max 2 ");
				op = Math.max(r.indexOf("-", op + 1), r.indexOf("+", op + 1));
				System.out.println("op " + op);
				if (op == -1) {
					return r;
				}
			}
			System.out.println("op nach -E " + op);
		}

		// solange wie ein Rechenoperator gefunden wird
		while (op > -1) {
			// Der Rechenoperator wird ausgelesen
			o = Character.toString(r.charAt(op));

			System.out.println(" strichloop r " + r);
			if (r.charAt(op - 1) == striche.charAt(4)) {
				System.out.println("-E als ersten OP");
				if (Math.min(r.indexOf("-", op + 1), r.indexOf("+", op + 1)) > 0) {
					System.out.println("min 2 ");
					op = Math.min(r.indexOf("-", op + 1), r.indexOf("+", op + 1));
					System.out.println("op " + op);
					o = Character.toString(r.charAt(op));

				} else {
					System.out.println("max 2 ");
					op = Math.max(r.indexOf("-", op + 1), r.indexOf("+", op + 1));
					System.out.println("op " + op);
					if (op == -1) {
						return r;
					}
					o = Character.toString(r.charAt(op));

				}
				System.out.println("op nach -E " + op);

			}
			// Es wird nach folgenden Rechenoperatoren gesucht um zu bestimmen, bis wohin in
			// der Rechnung die Zahl nach dem ersten gefundenen Rechenoperator reicht.
			for (int i = op + 2; i < r.length(); i++) {
				if (r.charAt(op - 1) == striche.charAt(4)) {
					System.out.println("-E als ersten OP");
					if (Math.min(r.indexOf("-", op + 1), r.indexOf("+", op + 1)) > 0) {
						System.out.println("min 2 ");
						op = Math.min(r.indexOf("-", op + 1), r.indexOf("+", op + 1));
						System.out.println("op " + op);
						i = op + 2;
					} else {
						System.out.println("max 2 ");
						op = Math.max(r.indexOf("-", op + 1), r.indexOf("+", op + 1));
						System.out.println("op " + op);
						i = op + 2;
						if (op == -1) {
							return r;
						}
					}
					System.out.println("op nach -E " + op);

				} else {
					nachp = r.length();
				}

				if (r.charAt(i) == striche.charAt(0)) {
					nachp = i;
					System.out.println("f+ bei " + i);
					break;
				} else if (r.charAt(i) == striche.charAt(1)) {
					nachp = i;
					System.out.println("f- bei " + i);
					if (r.charAt(i - 1) == striche.charAt(4)) {
						System.out.println("doch kein f- sondern E-");
						nachp = r.length();
						i++;
					} else {
						break;
					}
				} else {
					System.out.println("nicht " + i);
					if (i == r.length() - 1) {
						if (r.charAt(op - 1) == striche.charAt(4)) {
							System.out.println("-E als Ergebnis");
							break;
						} else {
							nachp = r.length();
						}
					}
				}
			}
			// Wenn kein folgender Rechenoperator gefunden wurde, befindet sich das Ende der
			// Zahl nach dem ersten gefundenen Rechenoperator am Ende des Strings.
			if (nachp <= op) {

				if (r.charAt(nachp - 1) == striche.charAt(4)) {
					System.out.println("-E bei nachp, sollte Fehler machen");
					break;
				} else {
					nachp = r.length();
				}
			}
			if (nachp > r.length()) {
				nachp = r.length();
			}
			if (op+2>=r.length()) {
				nachp=r.length();
			}
			
			System.out.println("erster strichoperator bei " + op + " ist ein " + o);
			if (nachp < r.length()) {
				System.out.println("folgender strichoperator bei " + nachp + "ist ein" + r.charAt(nachp));
			} else {
				System.out.println("kein folgender strichoperator");
			}
			System.out.println("hier r " + r + " hier vorp" + vorp + " hier op" + op + " hier nachp" + nachp);
			// vor und nach werden extrahiert.
			vor = Double.parseDouble(r.substring(vorp, op));
			System.out.println("vor a" + r.substring(vorp, op));
			System.out.println("Versuche nach zu setzen als " + r.substring(op + 1, nachp));
			nach = Double.parseDouble(r.substring(op + 1, nachp));
			System.out.println("vor b" + vor);
			System.out.println("nach b" + nach);

			// Die rausgesuchte Rechnung wird ausgef?hrt.
			if (o.equals("+")) {
				System.out.println("F?hre Rechnung aus:" + vor + "+" + nach);
				ergebnis = vor + nach;
				System.out.println("ergebnis " + ergebnis);
			} else if (o.equals("-")) {
				System.out.println("F?hre Rechnung aus:" + vor + "-" + nach);
				ergebnis = vor - nach;
				System.out.println("ergebnis " + ergebnis);
			} else {
				System.out.println("Fehler bei o, o ist: " + o + " von Position " + op);
				break;
			}
			System.out.println("vor c" + r.substring(0, vorp));
			System.out.println("Ergebnis " + Double.toString(ergebnis));
			System.out.println("nach c" + r.substring(nachp, r.length()));
			// Die Rechnung wird mit dem Ergebnis aktualisiert.
			r = r.substring(0, vorp) + Double.toString(ergebnis) + r.substring(nachp, r.length());
			r = formatierungshilfe(r);
			System.out.println("r strichzusammengesetzt" + r);
			// Es wird erneut nach einem Rechenoperator gesucht
			if (Math.min(r.indexOf("-", 1), r.indexOf("+", 1)) > 0) {
				op = Math.min(r.indexOf("-", 1), r.indexOf("+", 1));
			} else {
				op = Math.max(r.indexOf("-", 1), r.indexOf("+", 1));
			}
		}
		// Der ?berarbeitete String wird zur?ckgegeben.
		System.out.println("Der von strichrechnung ?berarbeitete String wird zur?ckgegeben. r" + r);
		r = formatierungshilfe(r);
		return r;
	}

	// Rechner.punktrechnung verlangt einen String und durchsucht diesen nach * und
	// / Operatoren. Anschlie?end wird ein Substring von nach einem zuvor gefundenen
	// Strichoperator oder dem Beginn des Strings bis zum
	// ersten gefundenen Punktoperator in einen Double gesetzt und ein Substring von
	// nach dem Operator bis zum n?chsten Operator oder dem Stringende ebenfalls in
	// ein weiteres Double gesetzt. Dann wird die Rechnung Double * bzw / Double
	// ausgef?hrt und im String die beiden Doubles und der Operator durch das
	// Ergebnis ersetzt. Wird wiederholt bis keine * und / Operatoren mehr im String
	// gefunden werden. Gibt den ?berarbeiteten String zur?ck.
	public static String punktrechnung(String r) {
		System.out.println("PUNKTRECHNUNG r" + r);
		// Der Inhalt dieses Strings wird f?r die gleich folgende Suche benutzt.
		String operatoren = "*/+-E";
		// Diese Variablen werden benutzt f?r...
		// die Position im String vor der (Zwischen-)/Rechnung.
		int vorp = 0;
		// die Zahl vor dem Rechenoperator.
		double vor = 0;
		// die Position im String nach der (Zwischen-)/Rechnung.
		int nachp = r.length();
		// die Zahl nach dem Rechenoperator.
		double nach = 0;
		// das (Zwischen-)/Ergebnis
		double ergebnis = 0;
		// die Position im String des Operators der (Zwischen-)/Rechnung.
		int op = 0;
		// den Rechenoperator
		String o;
		r = formatierungshilfe(r);
		// Es wird nach dem Rechenoperator gesucht.
		if (Math.min(r.indexOf("*", 1), r.indexOf("/", 1)) > 0) {
			System.out.println("min ");
			op = Math.min(r.indexOf("*", 1), r.indexOf("/", 1));
			System.out.println("op " + op);
		} else {
			System.out.println("max ");
			op = Math.max(r.indexOf("*", 1), r.indexOf("/", 1));
			System.out.println("op " + op);
		}
		// solange wie ein Rechenoperator gefunden wird
		while (op > -1) {
			System.out.println(" vorher " + r);
			// Der Rechenoperator wird ausgelesen
			o = Character.toString(r.charAt(op));
			// Es wird nach folgenden Rechenoperatoren gesucht um zu bestimmen, bis wohin in
			// der Rechnung die Zahl nach dem ersten gefundenen Rechenoperator reicht.
			for (int i = op + 2; i < r.length(); i++) {
				if (r.charAt(i) == operatoren.charAt(4)) {
					if (i == r.length() - 2) {
						nachp = r.length();
						System.out.println("kein nachp und E vorm Ende");
					} else {
						i++;
					}
				} else if (r.charAt(i) == operatoren.charAt(0)) {
					nachp = i;
					System.out.println("f* bei " + i);
					break;
				} else if (r.charAt(i) == operatoren.charAt(1)) {
					nachp = i;
					System.out.println("f/ bei " + i);
					break;
				} else if (r.charAt(i) == operatoren.charAt(2)) {
					nachp = i;
					System.out.println("f+ bei " + i);
					break;
				} else if (r.charAt(i) == operatoren.charAt(3)) {
					nachp = i;
					System.out.println("f- bei " + i);
					break;
				} else {
					System.out.println("nachp ist nicht bei " + i);
					if (i == r.length() - 1) {
						nachp = r.length();
						System.out.println("kein nachp");
					}
				}
			}
			// Wenn kein folgender Rechenoperator gefunden wurde, befindet sich das Ende der
			// Zahl nach dem ersten gefundenen Rechenoperator am Ende des Strings.
			if (nachp <= op) {
				nachp = r.length();
			}
			if (nachp > r.length()) {
				nachp = r.length();
			}
			if (op+2>=r.length()) {
				nachp=r.length();
			}
			// Es wird nach Strichrechnungsoperatoren vor dem ersten gefundenen
			// Punktrechnungsoperator gesucht um zu bestimmen, von woher in
			// der Rechnung die Zahl vor dem ersten gefundenen Punktechenoperator reicht.
			for (int i = op - 2; i > 0; i--) {
				if (r.charAt(i) == operatoren.charAt(2)) {
					vorp = i + 1;
					System.out.println("v+ bei " + i);
					break;
				} else if (r.charAt(i) == operatoren.charAt(3)) {
					if (r.charAt(i - 1) == operatoren.charAt(4)) {
						i--;
					} else {
						vorp = i + 1;
						System.out.println("v- bei " + i);
						break;
					}
				} else {
					System.out.println("v nicht bei " + i);
					if (i == r.length() - 1) {
						vorp = 0;
					}
				}
			} // vor und nach werden extrahiert.
			vor = Double.parseDouble(r.substring(vorp, op));
			System.out.println("versuche nach zu setzen auf " + r.substring(op + 1, nachp) + " op " + r.charAt(op)
					+ " at " + op + " nachp " + nachp + " r ist lang " + r.length());
			nach = Double.parseDouble(r.substring(op + 1, nachp));
			System.out.println("vor " + vor);
			System.out.println("nach " + nach);
			// Die rausgesuchte Rechnung wird ausgef?hrt.
			if (o.equals("*")) {
				System.out.println("F?hre Rechnung aus:" + vor + "*" + nach);
				ergebnis = vor * nach;
				System.out.println("ergebnis " + ergebnis);
			} else if (o.equals("/")) {
				System.out.println("F?hre Rechnung aus:" + vor + "/" + nach);
				ergebnis = vor / nach;
				System.out.println("ergebnis " + ergebnis);
			} else {
				System.out.println("Fehler bei o, o ist: " + o + " von Position " + op);
				break;
			}
			System.out.println("vor" + r.substring(0, vorp));
			System.out.println("ergebnis " + Double.toString(ergebnis));
			System.out.println("nach " + r.substring(nachp, r.length()));
			// Die Rechnung wird mit dem Ergebnis aktualisiert.
			if (vorp == 0) {
				System.out.println("ende punktrechnungloop " + "vorp ist 0 " + vorp);
				r = Double.toString(ergebnis) + r.substring(nachp, r.length());
			} else {
				System.out.println("ende punktrechnungloop " + "vorp ist nicht 0 " + vorp);
				r = r.substring(0, vorp) + Double.toString(ergebnis) + r.substring(nachp, r.length());
			}
			System.out.println("ende punktrechnungloop r" + r);
			r = formatierungshilfe(r);
			// Es wird erneut nach einem Rechenoperator gesucht
			if (Math.min(r.indexOf("*", 1), r.indexOf("/", 1)) > 0) {
				op = Math.min(r.indexOf("*", 1), r.indexOf("/", 1));
			} else {
				op = Math.max(r.indexOf("*", 1), r.indexOf("/", 1));
			}
		}
		r = formatierungshilfe(r);
		// Der ?berarbeitete String wird zur?ckgegeben.
		return r;
	}

	public static String formatierungshilfe(String r) {

		while (r.contains("--")) {
			r = r.replace("--", "+");
		}
		while (r.contains("++")) {
			r = r.replace("++", "+");
		}
		
	    r = r.replace(",", ".");
	    
	    
		
		System.out.println("formatiert zu " + r);
		return r;
	}
}
