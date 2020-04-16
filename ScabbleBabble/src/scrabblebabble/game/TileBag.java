package scrabblebabble.game;

import java.util.ArrayList;
import java.util.Random;

import scrabblebabble.handlers.util.EnumLetter;

public class TileBag {

	ArrayList<LetterTile> tiles;
	
	public TileBag() {
		tiles = new ArrayList<LetterTile>();
		initBag();
	}
	
	/**
	 * initializes the bag with all 100 tiles
	 * 
	 * for a list of all needed tile, google, i suggest making a file 
	 * that contains all the tile definitions and loading that so its easier
	 * 
	 * probably needs to be recursive so we can easily modify it
	 */
	public void initBag() {
		// TODO
		tiles.clear();
		tiles.add(new LetterTile(EnumLetter.A, 0));
		tiles.add(new LetterTile(EnumLetter.B, 1));
		tiles.add(new LetterTile(EnumLetter.C, 2));
		tiles.add(new LetterTile(EnumLetter.D, 3));
		tiles.add(new LetterTile(EnumLetter.E, 4));
		tiles.add(new LetterTile(EnumLetter.F, 5));
		tiles.add(new LetterTile(EnumLetter.G, 6));
		tiles.add(new LetterTile(EnumLetter.H, 7));
		tiles.add(new LetterTile(EnumLetter.I, 8));
		tiles.add(new LetterTile(EnumLetter.J, 9));
		tiles.add(new LetterTile(EnumLetter.K, 10));
		tiles.add(new LetterTile(EnumLetter.L, 11));
		tiles.add(new LetterTile(EnumLetter.M, 12));
		tiles.add(new LetterTile(EnumLetter.N, 13));
		tiles.add(new LetterTile(EnumLetter.O, 14));
		tiles.add(new LetterTile(EnumLetter.P, 15));
		tiles.add(new LetterTile(EnumLetter.Q, 16));
		tiles.add(new LetterTile(EnumLetter.R, 17));
		tiles.add(new LetterTile(EnumLetter.S, 18));
		tiles.add(new LetterTile(EnumLetter.T, 19));
		tiles.add(new LetterTile(EnumLetter.U, 20));
		tiles.add(new LetterTile(EnumLetter.V, 21));
		tiles.add(new LetterTile(EnumLetter.W, 22));
		tiles.add(new LetterTile(EnumLetter.X, 23));
		tiles.add(new LetterTile(EnumLetter.Y, 24));
		tiles.add(new LetterTile(EnumLetter.Z, 25));
		System.out.println("init bag");
	}
	
	/**
	 * suffles the bag
	 */
	@Deprecated
	public void randomize() {
		// TODO
	}
	
	/**
	 * draws a random index and removes the tile from the ArrayList as well then sends the LetterTile 
	 */
	public LetterTile drawRandom() {
		//TODO
		Random rand = new Random();	
		int r = rand.nextInt(tiles.size() - 1);
		LetterTile out = tiles.get(r);
		tiles.remove(r);
		return out;
	}
}
