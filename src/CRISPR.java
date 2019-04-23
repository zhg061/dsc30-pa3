/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */

import java.util.*;
/**
 * Gene Splicing CRISPR Simulator
 *
 * @author TODO
 * @since TODO
 */
public class CRISPR{

    /*Sequences to use/test your CRISPR functions on. Please add more as you test*/
    private static String simpleGenome = "ACATATA";

    private static String sequencedGenome = "AAATTCAAGCGAGGTGATTACAACAAATTTTGCTGATGGTTTAGGCGTA"
            + "CAATCCCCTAAAGAATATAATTAAGAAAATAGCATTCCTTGTCGCCTAGAATTACCTACCGGCGTCCACCATACCTTCG"
            + "ATATTCGCGCCCACTCTCCCATTAGTCGGCACAAGTGGATGTGTTGCGATTGCCCGCTAAGATATTCTAAGGCGTAACG"
            + "CAGATGAATATTCTACAGAGTTGCCGTACGCGTTGAACACTTCACGGATGATAGGAATTTGCGTATAGAGCGGGTCATT"
            + "GAAGGAGATTACACTCGTAGTTAACAACGGGCCCGGCTCTATCAGAACACGAGTGCCTTGAATAACATACTCATCACTA";


    private static String overlappingGuide = "UAU";
    private static String guideRNA = "CUAAUGU";
    private static String splicedGene = "TAGACAT";
    private static String guideRNA1 = "UGU";
    private static String splicedGene1 = "TAG";
    private static String simpleGenome1 = "ACATA";
    private static String guideRNA2 = "UA";
    private static String splicedGene2 = "CAT";
    private static String simpleGenome2 = "ATATA";
    private static String guideRNA3 = "UAU";
    private static String splicedGene3 = "CAT";
    private static String simpleGenome3 = "ATATA";
    private static String guideRNA4 = "CG";
    private static String splicedGene4 = "CAT";
    private static String simpleGenome4 = "ATATA";

    /**
     * Program Entry, this simply runs
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        /*Should print out ACATATA (unchanged)*/
        System.out.println(spliceDNA(simpleGenome, overlappingGuide, splicedGene));
        /*should print out “ACATAGTA” */
        System.out.println(spliceDNA(simpleGenome1, guideRNA1, splicedGene1));
        /*should print out “ATCATATCATA” */
        System.out.println(spliceDNA(simpleGenome2, guideRNA2, splicedGene2));
        /*should print out “ATATA” */
        System.out.println(spliceDNA(simpleGenome3, guideRNA3, splicedGene3));
        /*should print out “ATATA” */
        System.out.println(spliceDNA(simpleGenome4, guideRNA4, splicedGene4));

    }

    /**
     *  Simulate gene splicing on a genome using CRISPR
     *
     * @param genomeSequence initial DNA encoding
     * @param guideSequence guideRNA encoding
     * @param splicedSequence target insertion gene encoding
     * @return modified genome
     */
    public static String spliceDNA(String genomeSequence, String guideSequence, String splicedSequence) {
        DoublyLinkedList<Character> genome = new DoublyLinkedList<>();
        DoublyLinkedList<Character> guideRNA = new DoublyLinkedList<>();
        populateFromDNA(genome, genomeSequence);
        populateDNAFromRNA(guideRNA, guideSequence);
//        System.out.println("Genome: " + transcribeGeneticCode(genome));
//        System.out.println("Guide sequence: " + guideSequence);
//        System.out.println("Guide: " + transcribeGeneticCode(guideRNA));
        //create a new linked list of DNA called spliceSequence
        //iterating through the list that is created by metch method
        //if there is no overlapping of guided Node, the spliced node
        // is added to the genome at the indexes in the match list
        DoublyLinkedList<Character> splicedNode = new DoublyLinkedList<>();
        populateFromDNA(splicedNode, splicedSequence);
        int[] matchedIndex = genome.match(guideRNA);
        int shiftLength = 0;
        /* in the for loop, every matched index create a chance to insert the splicedNode,
        however, if there is an overlap of guideNode, that is, the difference between two indexes is
        less than the sixe of the guidedNode, the loop stops*/
        for (int i = 0; i < matchedIndex.length; i++) {
            if (i != matchedIndex.length-1 && (matchedIndex[i+1] - matchedIndex[i]) < guideRNA.size()) {
                i++;
                continue;
            }
            genome.splice(matchedIndex[i] + guideRNA.size() +shiftLength, splicedNode);
            shiftLength += splicedSequence.length();
        }
        return transcribeGeneticCode(genome);
    }

    /**
     * This is a direct encoding of the genetic code from the String to a LinkedList
     * @param dnaList to populate
     * @param dnaString DNA string encoding
     */
    public static void populateFromDNA(DoublyLinkedList<Character> dnaList, String dnaString) {
        //encoding the genetic code from the String to a LinkedList
        char[] stringToCharArray = dnaString.toCharArray();
        for (char output : stringToCharArray) {
            dnaList.add(output);
        }
    }

    /**
     * This is an encoding of of the DNA that binds with the RNA
     * Remember that DNA pairs up A-T C-G, and RNA pairs up A-U C-G
     * Thus the guide RNA AUCG would match with the DNA TAGC
     * @param dnaList to populate
     * @param rnaString RNA string encoding
     */
    public static void populateDNAFromRNA(DoublyLinkedList<Character> dnaList, String rnaString) {
        //encoding the genetic code from the String to a LinkedList also changes the value
        // of A, U, C, G to T, A, C, G when encountering them
        char[] stringToCharArray = rnaString.toCharArray();
        for (char output : stringToCharArray) {
            //System.out.println("Guide22: " + transcribeGeneticCode(dnaList));
            if (output == 'A') {
                dnaList.add('T');
            }
            else if (output == 'U') {
                dnaList.add('A');
            }
            else if (output == 'C') {
                dnaList.add('G');
            }
            else if (output == 'G') {
                dnaList.add('C');
            }
            else {
                dnaList.add(output);
            }
        }

    }

    /**
     * Recreate the original base sequence that was loaded into the list
     * @param geneticSequence list representation of the
     * @return base sequence of the genetic material
     */
    public static String transcribeGeneticCode(DoublyLinkedList<Character> geneticSequence) {
        String s = "";
        for (char c : geneticSequence) {
            s += c;
        }
        return s;
    }

}
