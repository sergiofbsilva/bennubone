package pt.ist.bennubone.coffee.util.json;

public class TreeNode {

	private String[] attributes;
	private TreeNode[] treeNodes;

	public TreeNode(String[] attributes, TreeNode... treeNodes) {
		this.attributes = attributes;
		this.treeNodes = treeNodes;
	}

	public TreeNode(String[] attributes) {
		this.attributes = attributes;
	}

	public String[] getAttributes() {
		return attributes;
	}

	public TreeNode[] getTreeNodes() {
		return treeNodes;
	}
}
