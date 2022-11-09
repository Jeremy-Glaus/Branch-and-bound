# Programmieraufgabe BnB-Algorithmus

## Programm ausführen
Das Programm lässt sich mit Maven ausführen. Sollte das fehlschlagen, kann mittels einer IDE das Programm kompiliert und ausgeführt werden. Für die Entwicklung wurde IntelliJ verwendet.

Die Berechnung wird mit einem Klick auf den Knopf ``Calculate`` im Tab ``Call Tree``. Vorher müssen allerdings noch Daten im Tab ``Data`` erfasst werden.


## Benutzereingabe
Eingaben können wie folgt vorgenommen werden:

### Tab "Call Tree"
``Calculate`` berechnet den Datensatz und stellt den rekursiven Call Tree grafisch dar. Wegen eines Bugs muss der Knopf 2x gedrückt werden, wenn die Option ``Presort`` aktiv ist.

``Presort`` weist an, vor dem Ausführen des Algorithmus die Eingabedaten zu sortieren. Die Sortierung ist permanent und überschreibt die Daten.

``Rucksack Grösse`` ist die maximale Traglast des Rucksacks

``Copy Data`` gibt an, ob die Daten kopiert werden sollen oder nicht. Das heisst, bei der Ausführung des Algorithmus werden teilweise Daten gelöscht.

### Tab "Data"
Da kann ein manuelles ``volume`` und dazugehörige ``value`` erstellt werden. Ebenfalls ist es möglich, eine Anzahl für zufällig zu generierende Datensätze einzutragen.

## Hinweise zu den Eingabefeldern
Die Benutzereingaben **müssen** numerische Werte sein, eine Validierung der Eingabe wird nicht vorgenommen.

Die Option ``Zufällige Daten generieren``  überschreibt den bisherigen Datensatz. Es werden auf 2 Stellen gerundete Doubles zwischen 1 und 20 generiert.

## Ergebnisse ablesen
Die Ergebnisse werden im Tab "Call Tree" grafisch als Baum dargestellt. In den einzelnen Nodes sind folgende Angaben enthalten:
- Verfügbares Volumen im Rucksack
- Aktueller Wert der Gegenstände im Rucksack
- Aktuell zu untersuchendes Element

In der Reihe des "Calculate"-Buttons werden verschiedene Werte angegeben:
- ``Best Value:`` Ergebnis des Algorithmus
- ``Übriger Platz:`` Übriger Platz nach Berechnung der ``best Value``
- ``Iterations:`` Anzahl der rekursiven Aufrufe
- ``Iterations saved:`` Vergleich des BnB-Algorithmus mit der Brute Force Methode. $\mathcal{O}(2^n)$

### Farbbeschreibung
Die Nodes in der grafischen Darstellung haben verschiedene Bedeutungen. Diese richten sich nach dem Status des BnB-Algorithmus.

| Farbe | Beschreibung |
| - | - |
| Weiss | Standard |
| Rot | Ast abschneiden |
| Orange | Zu schwer um mitzunehmen |
| Grün | Result-Node. Entählt Endresultat des Branches, welches aktuell als bestes Resultat galt

## Designentscheide
Die ``toString``-Methoden sind so konzipiert, dass diese valides JSON ausgeben. Das ist nützlich, denn so kann ein Node genauer analysiert werden. Nach jeder Ausführung des BnB-Algorithmus wird das Root-Node in der Konsole ausgegeben.

Die Daten sind von überall her zugänglich und manipulierbar.

Für die grafische Darstellung der Nodes wird die Funktion $f(y) = \frac{i}{x}$ für die Berechnung der $x$-Koordinate verwendet. Mit konstanten Distanzen überlappen sich ansonsten die Nodes. Die Distanz für $y$ ist konstant.
$i$ setzt sich aus $1000*iterations$ zusammen. So ist das Programm in der Lage, auf variable Grössen des Call Trees zu reagieren.

## Fragestellungen
Während ich mich mit dem Algorithmus beschäftigt habe, sind mir einige Dinge aufgefallen, die ich dann gleich etwas näher behandelt habe.
### Lohnt es sich, die Inputdaten zu sortieren?
Während dem Experimentieren ist mir aufgefallen, dass die Anzahl der Iterationen deutlich abnimmt, wenn die Liste der Values mit hohen Zahlen beginnen. Um dem auf die Spur zu gehen, habe ich die Option ``Presort`` eingebaut und siehe da: Wenn die Values im Vorhinein sortiert werden, ist der Algorithmus effizienter.

### Kann die Grösse des Inputs verkleinert werden?
Beim Aufruf des BnB-Algorithmus habe ich anfänglich vergessen, die Daten als Kopie mitzugeben. Als Folge wurde beim Durchlaufen des Algorithmus die Originaldaten bearbeitet und Daten wurden gelöscht. Aufgefallen ist mir, dass jeweils einige Durchführungen das Ergebnis unverändert gelassen haben. Allerdings wurden die Anzahl der rekursiven Aufrufe kleiner, da es weniger Daten zu bearbeiten gab. Nachdem ich den Fehler behoben habe, habe ich noch eine Funktion eingebaut, um dem genauer zum Grund zu gehen. Das bietet Möglichkeiten für Optimierungen, allerdings kann die Korrektheit des Ergebnisses darunter leiden. Eine genauere Untersuchung ob und wie das sinnvoll ist, steht aber noch aus.

### Wann ist Brute Force effektiver?
Beim BnB-Algorithms werden in den Blättern eines Branches die Evaluationen vorgenommen und die Informationen über den Branch gespeichert. Das hat zur Folge, dass der Call Tree um eine Schicht tiefer wird. Brute Force ist damit effizienter, wenn der BnB-Algorithmus sich in einem Worst-Case-Szenario befindet.

## Weiterführung
- Tab zur Analyse der Komplexität fertigstellen
- Separation of Concern einführen
- Validierung der Userinputs
- Fehlerbehandlung
- Einzelne Datensätze wieder löschen
- Optimierung bei der Darstellung der Tabelle: Aktuell wird diese bei jeder Änderung neu erstellt
- Parallelisierung einbauen
- Responsiveness einfügen
- Konzept für die Darstellung und den Distanzen verbessern
- Compiler-Warnungen bearbeiten
