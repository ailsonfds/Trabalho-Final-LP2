/**
 * Package to generate a Trie object containing indexed words
 */
package index;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import exceptions.BlackListException;
import exceptions.EmptySearchException;
import exceptions.EmptyWordException;
import exceptions.FileAlreadyExistsException;
import exceptions.FileNotFoundException;
import exceptions.FileTypeException;
import exceptions.TrieInsertionException;
import utils.Trie;
import utils.TrieNode;

/**
 * An indexer of files.
 * 
 * It is a boundary class
 * {@link https://www.ibm.com/support/knowledgecenter/en/SS6RBX_11.4.2/com.ibm.sa.oomethod.doc/topics/c_Boundary_Class.html}
 * 
 * 
 * @author Ailson Forte dos Santos
 * @author Larissa Gilliane Melo de Moura
 */
/*
 * (non-Javadoc) Essa classe eh apenas uma classe de interface com outras
 * classes, ou seja, ela eh classe de fronteira(Ela naum eh <interface> apenas
 * interage com outras classes)
 */
public class Indexer {
	Parser p;
	ArrayList<String> files;
	String extension;
	Trie trieBlack;

	/**
	 * Constructor
	 * 
	 */
	public Indexer() {
		files = new ArrayList<>();
		p = new Parser();
		extension = ".txt";
		trieBlack = new Trie();
	}

	/**
	 * Add a document to the database
	 * 
	 * @param String
	 *            filename The Filename that will be add in the database
	 * @throws FileTypeException
	 * @throws FileAlreadyExistsException
	 * 
	 */
	public void addDocument(String filename) throws FileTypeException, FileAlreadyExistsException {
		if (!(filename.toLowerCase().contains(extension.toLowerCase()))) {
			throw new FileTypeException("Please, insert a file with .txt extension.");
		} else if ((files.contains(filename))) {
			throw new FileAlreadyExistsException("This file already exists in the system.");
		} else {
			if (filename != null) {
				p.fillTrie(filename);
			}
			if (p.fillTrie(filename) != null) {
				files.add(filename);
			}
		}
	}

	/**
	 * Remove a document to the database
	 * 
	 * @param filename
	 *            The Filename that will be removed in the database
	 * @throws FileTypeException
	 * @throws FileAlreadyExistsException
	 * 
	 */
	public void removeDocument(String filename) throws FileNotFoundException {
		if (!(files.contains(filename))) {
			throw new FileNotFoundException("You can't remove the file because he is not in the system");
		} else {
			files.remove(filename);
			p.removeFromTrie(filename);
		}
	}

	/**
	 * Update the documents from the database
	 * 
	 * @param filename
	 *            The Filename that will be removed in the database
	 * 
	 * @throws FileTypeException
	 * @throws FileNotFoundException 
	 */
	public void updateDocuments(String filename) throws FileTypeException, FileNotFoundException {
		if (!(files.contains(filename)))
		{
			throw new FileNotFoundException("This file is not in the database");
		}
		files.remove(filename);
		p.removeFromTrie(filename);
		if (!(filename.toLowerCase().contains(extension.toLowerCase()))) {
			throw new FileTypeException("Please, insert a file with .txt extension.");
		} else {
			if (filename != null) {
				p.fillTrie(filename);
			}
			if (p.fillTrie(filename) != null) {
				files.add(filename);
			}
		}

	}

	/**
	 * Returns a list of documents that are present on database and the number of
	 * words.
	 * 
	 * @return a list of documents
	 */
	public ArrayList<String> listDocuments() {
		ArrayList<String> result = new ArrayList<>();
		int cont = 0;
		Collections.sort(files);
		for (String file : files) {
			cont++;
			String aux = "Arquivo " + cont + ": " + file + " com: " + p.contWords(file) + " palavras. ";
			result.add(aux);
		}
		return result;
	}

	/**
	 * Receive a word and do adjusts the special characters so that can be find on
	 * the Trie.
	 * 
	 * @param word
	 *            A word that will be treat
	 * @return The word after the treatment
	 */
	public String treatWord(String word) {
		word = Normalizer.normalize(word, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		word = word.replaceAll("[^-a-zA-Z0-9]", "");
		return word;
	}

	/**
	 * Receive a word and search on the Trie, if the results are found in a file OR another, that will be saved.
	 * 
	 * @param word
	 *            The word that will be searched.
	 * @return A HashMap with words and your information.
	 */
	public HashMap<String, TrieNode> searchOR(String word) throws EmptyWordException, EmptySearchException, BlackListException {
		if (word == null) {
			throw new EmptyWordException("This search is empty, please insert a valid text");
		} else {
			HashMap<String, TrieNode> retorno = new HashMap<>();
			Trie tree = p.getTree();
			TrieNode node = null;
			String[] words = word.split(" ");
			if (words.length > 1) {
				for (String w : words) {
					String aux = treatWord(w);
					if (checkOnBlacklist(aux)) {
						throw new BlackListException("You can't search this, it's on the blacklist.");
					} else {
						node = tree.search(aux);
						if (node != null) {
							retorno.put(w, node);
						} else {
							throw new EmptySearchException("No results for: " + w);
						}
					}
				}
			} else {
				String aux = treatWord(word);
				if (checkOnBlacklist(aux)) {
					throw new BlackListException("You can't search this, it's on the blacklist.");
				} else {
					node = tree.search(aux);
					if (node != null) {
						retorno.put(word, node);
					} else {
						throw new EmptySearchException("No results for: " + word);
					}
				}
			}
			if (retorno.isEmpty()) {
				throw new EmptySearchException("No results for: " + word);
			}
			return retorno;
		}
	}

	/**
	 * Receive a word and search on the Trie, if the results are found in a file AND another, that will be saved.
	 * 
	 * @param word
	 *            The word that will be searched.
	 * @return A HashMap with words and your information.
	 */
	public HashMap<String, TrieNode> searchAND(String word) throws EmptyWordException, EmptySearchException, BlackListException {
		if (word == null) {
			throw new EmptyWordException("This search is empty, please insert a valid text");
		}
		HashMap<String, TrieNode> retorno = new HashMap<>();
		Trie tree = p.getTree();
		TrieNode node = null;
		String[] words = word.split(" ");
		if (words.length > 1) {
			ArrayList<TrieNode> nodesSearched = new ArrayList<>();
			// Busca na árvore
			for (String w : words) {
				String aux = treatWord(w);
				if (checkOnBlacklist(aux)) {
					throw new BlackListException("You can't search this, it's on the blacklist.");
				} else {
					node = tree.search(aux);
					if (node != null) {
						nodesSearched.add(node);
					}
				}
			}
			HashSet<String> nodesFileName = new HashSet<>();
			// Captura o nome dos arquivos
			for (TrieNode n : nodesSearched) {
				nodesFileName.addAll(n.getValue().keySet());
			}
			HashMap<String, ArrayList<TrieNode>> occurences = new HashMap<>();
			// Verifica a existência dos arquivos na ocorrência das palavras
			for (String key : nodesFileName) {
				for (TrieNode n : nodesSearched) {
					if (n.getValue().containsKey(key)) {
						if (occurences.containsKey(key)) {
							occurences.get(key).add(n);
						} else {
							ArrayList<TrieNode> aux = new ArrayList<>();
							aux.add(n);
							occurences.put(key, aux);
						}
					}
				}
			}
			// adiciona ao retorno a busca
			for (String w : occurences.keySet()) {
				if (nodesSearched.size() == occurences.get(w).size()) {
					for (String s : words) {
						String aux = treatWord(s);
						if (checkOnBlacklist(aux)) {
							throw new BlackListException("You can't search this, it's on the blacklist.");
						}
						retorno.put(s, tree.search(aux));
					}
				}
			}
			if (retorno.isEmpty()) {
				throw new EmptySearchException("No results for: " + word);
			}
			return retorno;
		} else {
			String aux = treatWord(word);
			if (checkOnBlacklist(aux)) {
				throw new BlackListException("You can't search this, it's on the blacklist.");
			}
			node = tree.search(aux);
			if (node != null) {
				retorno.put(word, node);
			} else {
				throw new EmptySearchException("No results for: " + word);
			}
			if (retorno.isEmpty()) {
				throw new EmptySearchException("No results for: " + word);
			}
			return retorno;
		}

	}

	/**
	 * Add a blacklist of words
	 * 
	 * @param blacklist
	 *            A Blacklist with a list of words.
	 * @throws TrieInsertionException
	 * @throws FileTypeException
	 * 
	 * @return true if was correct add in the database.
	 */
	public boolean readBlacklist(String blacklist) throws TrieInsertionException, FileTypeException {
		if (!(blacklist.toLowerCase().contains(extension.toLowerCase()))) {
			throw new FileTypeException("Please, insert a file with .txt extension.");
		} else {
			Parser pBlack = new Parser();
			trieBlack = pBlack.fillTrie(blacklist);
			if (trieBlack != null) {
				return true;
			} else {
				throw new TrieInsertionException("The document was not indexed in the system.");
			}
		}
	}

	/**
	 * Check if a word is present in the Blacklist
	 * 
	 * @param word
	 *            The word that will be checked.
	 * 
	 * @return true if was found.
	 */
	public boolean checkOnBlacklist(String word) {
		String[] words = word.split(" ");
		TrieNode nodeBlack = trieBlack.search(word);
		if (words.length > 1) {
			for (String w : words) {
				String aux = treatWord(w);
				nodeBlack = trieBlack.search(aux);
				if (nodeBlack != null) {
					return true;
				}
			}
		} else {
			String aux = treatWord(word);
			nodeBlack = trieBlack.search(aux);
			if (nodeBlack != null) {
				return true; // It's on blacklist
			}
		}
		return false;
	}

}