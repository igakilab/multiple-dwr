package jp.igakilab.dwr.mybatis;

import java.util.ArrayList;

/**
 * Deck
 */
public class Deck {

  private String name = new String();
  private ArrayList<Integer> cards = new ArrayList<>();

  public String getName() {
    return name;
  }

  public ArrayList<Integer> getCards() {
    return cards;
  }

  public void setCards(ArrayList<Integer> cards) {
    this.cards = cards;
  }

  public void setName(String name) {
    this.name = name;
  }

}
