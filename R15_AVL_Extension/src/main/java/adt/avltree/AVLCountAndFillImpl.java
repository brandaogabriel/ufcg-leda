package adt.avltree;

import adt.bst.BSTNode;
import adt.bt.Util;

import java.util.*;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);

		if(balance > 1)
			pesaPraEsquerda(node);

		else if (balance < -1)
			pesaPraDireita(node);

	}

	private void pesaPraDireita(BSTNode<T> node) {
		BSTNode<T> aux;

		if (calculateBalance((BSTNode<T>) node.getRight()) < 0){

			aux = Util.leftRotation(node);
			this.RRcounter++;

		}

		else {

			Util.rightRotation((BSTNode<T>) node.getRight());
			aux = Util.leftRotation(node);
			this.RLcounter++;

		}

		if(aux.getParent() == null)
			super.root = aux;

	}

	private void pesaPraEsquerda(BSTNode<T> node) {
		BSTNode<T> aux;

		if(calculateBalance((BSTNode<T>) node.getLeft()) > 0) {

			aux = Util.rightRotation(node);
			this.LLcounter++;

		}

		else{

			Util.leftRotation((BSTNode<T>) node.getLeft());
			aux = Util.rightRotation(node);
			this.LRcounter++;

		}

		if(aux.getParent() == null)
			super.root = aux;

	}


	@Override
	public void fillWithoutRebalance(T[] array) {
		if (array != null && array.length > 0) {

			Arrays.sort(array);
			addWithoutRebalance(array, 0, array.length);

		}

	}

	private void addWithoutRebalance(T[] array, int begin, int end) {

		if(begin != end){

			int middle = (end + begin) / 2;
			superInsert(array[middle]);
			addWithoutRebalance(array, begin, middle);
			addWithoutRebalance(array, middle + 1, end);

		}

	}

}
