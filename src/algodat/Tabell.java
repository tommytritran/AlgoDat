/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algodat;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Tommy
 */
public class Tabell {

    public static int inversjoner(int[] a) {
        int antall = 0;  // antall inversjoner
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    antall++;  // en inversjon siden i < j
                }
            }
        }
        return antall;
    }

    public static boolean nestePermutasjon(int[] a) {
        int i = a.length - 2;                    // i starter nest bakerst
        while (i >= 0 && a[i] > a[i + 1]) {
            i--;   // går mot venstre
        }
        if (i < 0) {
            return false;                 // a = {n, n-1, . . . , 2, 1}
        }
        int j = a.length - 1;                    // j starter bakerst
        while (a[j] < a[i]) {
            j--;                 // stopper når a[j] > a[i] 
        }
        bytt(a, i, j);
        snu(a, i + 1);               // bytter og snur

        return true;                             // en ny permutasjon
    }

    public static void snu(int[] a, int v, int h) // snur intervallet a[v:h]
    {
        while (v < h) {
            bytt(a, v++, h--);
        }
    }

    public static void snu(int[] a, int v) // snur fra og med v og ut tabellen
    {
        snu(a, v, a.length - 1);
    }

    public static void snu(int[] a) // snur hele tabellen
    {
        snu(a, 0, a.length - 1);
    }

    public static int[] nestMin(int[] a) {
        int n = a.length;   // tabellens lengde

        if (n < 2) {
            throw new IllegalArgumentException("a.length(" + n + ") < 2!");
        }

        int m = Tabell.min(a);   // m er posisjonen til tabellens minste verdi

        int nm = 0;   // nm står for nestmin

        if (m == 0) {
            nm = Tabell.min(a, 1, n);              // leter i a[1:n>
        } else if (m == n - 1) {
            nm = Tabell.min(a, 0, n - 1);     // leter i a[0:n-1>
        } else {
            int mv = Tabell.min(a, 0, m);                   // leter i a[0:m>
            int mh = Tabell.min(a, m + 1, n);                 // leter i a[m+1:n>
            nm = a[mh] < a[mv] ? mh : mv;           // hvem er minst?
        }

        int[] b = {m, nm};
        return b;  // minste verdi i b[0], nest minste i b[1]

    } // nestMin

    public static int[] nestMaks(int[] a) // ny versjon
    {
        int n = a.length;     // tabellens lengde
        if (n < 2) {
            throw // må ha minst to verdier
                    new java.util.NoSuchElementException("a.length(" + n + ") < 2!");
        }

        int m = 0;      // m er posisjonen til største verdi
        int nm = 1;     // nm er posisjonen til nest største verdi

        // bytter om m og nm hvis a[1] er større enn a[0]
        if (a[1] > a[0]) {
            m = 1;
            nm = 0;
        }

        int maksverdi = a[m];                // største verdi
        int nestmaksverdi = a[nm];           // nest største verdi

        for (int i = 2; i < n; i++) {
            if (a[i] > nestmaksverdi) {
                if (a[i] > maksverdi) {
                    nm = m;
                    nestmaksverdi = maksverdi;     // ny nest størst

                    m = i;
                    maksverdi = a[m];              // ny størst
                } else {
                    nm = i;
                    nestmaksverdi = a[nm];         // ny nest størst
                }
            }
        } // for

        return new int[]{m, nm};    // n i posisjon 0, nm i posisjon 1

    } // nestMaks

    public static void fratilKontroll(int tablengde, int fra, int til) {
        if (fra < 0) // fra er negativ
        {
            throw new ArrayIndexOutOfBoundsException("fra(" + fra + ") er negativ!");
        }

        if (til > tablengde) // til er utenfor tabellen
        {
            throw new ArrayIndexOutOfBoundsException("til(" + til + ") > tablengde(" + tablengde + ")");
        }

        if (fra > til) // fra er større enn til
        {
            throw new IllegalArgumentException("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
        }
    }

    public static void skriv(int[] a, int fra, int til) {
        fratilKontroll(a.length, fra, til);
        if (til - fra > 0) {
            System.out.print(a[fra]);
        }
        for (int i = fra + 1; i < til; i++) {
            System.out.print(" " + a[i]);
        }
    }

    public static void skriv(int[] a) {
        skriv(a, 0, a.length);
    }

    public static int maks(String[] a) // legges i class Tabell
    {
        int m = 0;                          // indeks til største verdi
        String maksverdi = a[0];            // største verdi

        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(maksverdi) > 0) {
                maksverdi = a[i];  // største verdi oppdateres
                m = i;             // indeks til største verdi oppdaters
            }
        }
        return m;  // returnerer posisjonen til største verdi
    }

    public static <T extends Comparable<? super T>> void innsettingssortering(T[] a) {
        for (int i = 1; i < a.length; i++) // starter med i = 1
        {
            T verdi = a[i];        // verdi er et tabellelemnet 
            int j = i - 1;        // j er en indeks
            // sammenligner og forskyver:
            for (; j >= 0 && verdi.compareTo(a[j]) < 0; j--) {
                a[j + 1] = a[j];
            }

            a[j + 1] = verdi;      // j + 1 er rett sortert plass
        }
    }

    public static <T extends Comparable<? super T>> int maks(T[] a) {
        int m = 0;                     // indeks til største verdi
        T maksverdi = a[0];            // største verdi
        System.out.println("test");
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(maksverdi) > 0) {
                maksverdi = a[i];  // største verdi oppdateres
                m = i;             // indeks til største verdi oppdaters
            }
        }
        return m;  // returnerer posisjonen til største verdi
    } // maks

    public static int maks(double[] a) // legges i class Tabell
    {
        int m = 0;                           // indeks til største verdi
        double maksverdi = a[0];             // største verdi

        for (int i = 1; i < a.length; i++) {
            if (a[i] > maksverdi) {
                maksverdi = a[i];     // største verdi oppdateres
                m = i;                // indeks til største verdi oppdaters
            }
        }
        return m;     // returnerer posisjonen til største verdi
    }

    public static int maks(char[] a) // legges i class Tabell
    {
        int m = 0;                           // indeks til største verdi
        char maksverdi = a[0];             // største verdi

        for (int i = 1; i < a.length; i++) {
            if (a[i] > maksverdi) {
                maksverdi = a[i];     // største verdi oppdateres
                m = i;              // indeks til største verdi oppdaters
            }
        }
        return m;     // returnerer posisjonen til største verdi
    }

    public static int maks(Integer[] a) // legges i class Tabell
    {
        int m = 0;                           // indeks til største verdi
        Integer maksverdi = a[0];             // største verdi

        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(maksverdi) > 0) {
                maksverdi = a[i];     // største verdi oppdateres
                m = i;              // indeks til største verdi oppdaters
            }
        }
        return m;     // returnerer posisjonen til største verdi
    }

    public static int maks(int[] a, int fra, int til) {
        int sist = til - 1;            // siste posisjon i tabellen
        int m = fra;                   // indeks til største verdi
        int maksverdi = a[fra];        // største verdi
        int temp = a[sist];            // tar vare på siste verdi
        a[sist] = 0x7fffffff;          // legger tallet 2147483647 sist

        for (int i = fra + 1;; i++) // i starter med 1
        {
            if (a[i] >= maksverdi) // denne blir sann til slutt
            {
                if (i == sist) // sjekker om vi er ferdige
                {
                    a[sist] = temp;          // legger siste verdi tilbake
                    return temp >= maksverdi ? sist : m;   // er siste størst?
                } else {
                    maksverdi = a[i];        // maksverdi oppdateres
                    m = i;                   // m oppdateres
                }
            }
        }
    } // maks 

    public static int maks(int[] a) // bruker hele tabellen
    {
        return maks(a, 0, a.length);     // kaller metoden over
    }

    public static int min(int[] a, int fra, int til) {
        if (fra < 0 || til > a.length || fra >= til) {
            throw new IllegalArgumentException("Illegalt intervall!");
        }

        int m = fra;             // indeks til minste verdi i a[fra:til>
        int minverdi = a[fra];   // minste verdi i a[fra:til>

        for (int i = fra + 1; i < til; i++) {
            if (a[i] < minverdi) {
                m = i;               // indeks til minste verdi oppdateres
                minverdi = a[m];     // minste verdi oppdateres
            }
        }

        return m;  // posisjonen til minste verdi i a[fra:til>
    }

    public static int min(int[] a) // bruker hele tabellen
    {
        return min(a, 0, a.length);     // kaller metoden over
    }

    public static void bytt(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static int[] randPerm(int n) // en effektiv versjon
    {
        Random r = new Random();         // en randomgenerator
        int[] a = new int[n];            // en tabell med plass til n tall

        Arrays.setAll(a, i -> i + 1);    // legger inn tallene 1, 2, . , n

        for (int k = n - 1; k > 0; k--) // løkke som går n - 1 ganger
        {
            int i = r.nextInt(k + 1);        // en tilfeldig tall fra 0 til k
            bytt(a, k, i);                   // bytter om
        }

        return a;                        // permutasjonen returneres
    }
}
