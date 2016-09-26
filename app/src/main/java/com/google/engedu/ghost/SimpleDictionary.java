package com.google.engedu.ghost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
              words.add(line.trim());
        }
    }

    @Override
    public boolean isWord(String word) {
        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {
        if(prefix==null){
            Random random = new Random();
            int n= random.nextInt(words.size());
            return words.get(n);
        }
        else{
            return binarySearchToFindWord(prefix,0,words.size()-1);
        }
    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        return null;
    }

    private String binarySearchToFindWord(String prefix,int beg,int end){
        int mid =  (beg+end)/2;
        if(end>beg){
            String midString = words.get(mid).toString();

            if(midString.equals(prefix)){
                return binarySearchToFindWord(prefix,mid+1,mid);
            }
            else if(midString.length()>(prefix.length()-1) && midString.substring(0,prefix.length()).equals(prefix)){
                return midString;
            }
            else if(midString.compareTo(prefix)<0){
                return binarySearchToFindWord(prefix,mid+1,end);
            }
            else if(midString.compareTo(prefix)>0){
                return binarySearchToFindWord(prefix,beg,mid-1);
            }
        }

        return null;
    }
}
