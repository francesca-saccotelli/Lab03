package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.*;

public class Dictionary {
	
	List<String>dizionario=new LinkedList<String>();

	public void loadDictionary(String language) {
		if(language.compareTo("Italian")==0) {
			try {
				FileReader fr=new FileReader("src\\main\\resources\\Italian.txt");
				BufferedReader br=new BufferedReader(fr);
				String word;
				while((word=br.readLine())!=null) {
					//Aggiungere parola alla struttura dati
					dizionario.add(word);
				}
				br.close();
			}catch(IOException e) {
				System.out.println("Errore nella lettera del file");
			}
		}else if(language.compareTo("English")==0) {
			try {
				FileReader fr=new FileReader("src\\main\\resources\\English.txt");
				BufferedReader br=new BufferedReader(fr);
				String word;
				while((word=br.readLine())!=null) {
					//Aggiungere parola alla struttura dati
					dizionario.add(word);
				}
				br.close();
			}catch(IOException e) {
				System.out.println("Errore nella lettera del file");
			}
		}
	}
	
	public List<RichWord>spellCheckText(List<String>inputTextList){
		List<RichWord>richWord=new LinkedList<RichWord>();
		for(String s:inputTextList){
			if(dizionario.contains(s)) {
				RichWord rc=new RichWord(s,true);
				richWord.add(rc);
			}else {
				RichWord rc=new RichWord(s,false);
				richWord.add(rc);
				}
		}
		return richWord;
	}
	
	public List<RichWord>spellCheckTextLinear(List<String>inputTextList){
		List<RichWord>richWord=new LinkedList<RichWord>();
		for(String s:inputTextList){
			boolean trovato=false;
			for(String ss:dizionario) {
				if(ss.compareTo(s)==0) {
					trovato=true;
				}
			}
			if(trovato==false) {
				RichWord rc=new RichWord(s,false);
				richWord.add(rc);
			}else {
				RichWord rc=new RichWord(s,true);
				richWord.add(rc);
			}
		}
		return richWord;
	}
	public List<RichWord>spellCheckTextDichotomic(List<String>inputTextList){
		List<RichWord>richWord=new LinkedList<RichWord>();
		for(String s:inputTextList){
			if (binarySearch(s.toLowerCase())) {
				RichWord rc=new RichWord(s,true);
				richWord.add(rc);	
			}
			else {
				RichWord rc=new RichWord(s,false);
				richWord.add(rc);
			}
		}
		return richWord;
	}
	private boolean binarySearch(String stemp) {
		int inizio = 0;
		int fine = dizionario.size();

		while (inizio != fine) {
			int medio = inizio + (fine - inizio) / 2;
			if (stemp.compareToIgnoreCase(dizionario.get(medio)) == 0) {
				return true;
			} else if (stemp.compareToIgnoreCase(dizionario.get(medio)) > 0) {
				inizio = medio + 1;
			} else {
				fine = medio;
			}
        }
        return false;
     }
}
