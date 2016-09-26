# Ghost
Android Game (Unit 3 and 4 of CS with Android) 

Ghost is a word game in which players take turns adding individual letters to a growing word fragment, trying not to be the one to complete a valid word. Each fragment must be the beginning of an actual word, and, for our purposes, we will consider 4 to be the minimum word length. The player who completes a word or creates a fragment that is not the prefix of a word loses the round. <br>

So on player 1's turn, they can:<br>

1.challenge player 2's word if they think player 2 has formed a valid word of at least 4 letters. If the fragment is a word, then player 1 wins; if the fragment is not a word, then player 2 wins.<br>
2.challenge player 2's word if they think that no word can be formed with the current fragment. Then, player 2 must provide a valid word starting with the current fragment or lose.<br>
3.add a letter to move the fragment towards a valid word<br>
4.attempt to bluff player 2 by adding a letter that doesn't move the fragment towards a valid word
You can read more about this game on [Wikipedia](https://en.wikipedia.org/wiki/Ghost_(game)).<br>
