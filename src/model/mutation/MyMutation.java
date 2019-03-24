package model.mutation;

import java.util.ArrayList;
import java.util.List;

import model.entities.Individual;
import model.misc.TreeNode;

public class MyMutation implements Mutation {

	public List<Integer> _postOrder;
	
	@Override
	public Individual apply(Individual origin) {
		_postOrder = new ArrayList<Integer>();
		TreeNode root=constructTree(origin);
		
		postOrderTraversal(root);
		
		Individual result=new Individual(origin);
		for(int i=0;i<result.length();i++) {
			result.setGene(i, _postOrder.get(i));
		}
		
		if(!result.valid()) {
			throw new IllegalArgumentException("stop");
		}
		
		return result;
	}

	private void postOrderTraversal(TreeNode root) {
		if(root==null)
			return;
		else {
			postOrderTraversal(root.getLeft());
			postOrderTraversal(root.getRight());
			_postOrder.add(root.getValue());
		}
		
	}

	private TreeNode constructTree(Individual origin) {
		List<TreeNode> cache = new ArrayList<TreeNode>();
		for(int i=0;i<origin.length();i++) {
			cache.add(new TreeNode(origin.getGene(i)));
		}
		for(int i=0;i<cache.size();i++) {
			if((i+1)*2-1<cache.size()){
				cache.get(i).setLeft(cache.get((i+1)*2-1));
			}
			if((i+1)*2<cache.size()){
				cache.get(i).setRight(cache.get((i+1)*2));
			}
		}
		return cache.get(0);
	}

}
