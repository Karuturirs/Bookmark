package com.bookmark.restservices.client;

import java.util.ArrayList;
import java.util.List;

public class DTONodeDetail {
    private int nodeId;
    private String nodeName;
    private String nodeType;
    private String parentId;       
    public DTONodeDetail() {
    }

    public DTONodeDetail(int nodeId, String nodeName, String parentId) {
        this.nodeId = nodeId;
        this.nodeName = nodeName;
        this.parentId = parentId;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


    public static DTONodeDetail getDTO(int nodeId, String nodeName,String nodeType ,String parentID ) {
        DTONodeDetail dto = new DTONodeDetail();
        dto.setNodeId(nodeId);
        dto.setNodeName(nodeName);
        dto.setParentId(parentID);
        dto.setNodeType(nodeType);

        return dto;
    }

    private static List<DTONodeDetail> selectChildren(int parentId, List<DTONodeDetail> tree) {
        List<DTONodeDetail> result = new ArrayList<DTONodeDetail>();
        for (DTONodeDetail d : tree) {
            if (d.getParentId().equals(parentId)) {
                result.add(d);
            }
        }
        return result;
    }

    public static void displayTree(DTONodeDetail dto, int level, List<DTONodeDetail> tree) {
 
        List<DTONodeDetail> childs = selectChildren(dto.getNodeId(),tree);
        String space = "";
        for (int i = 0; i < level; i++) {
            space += "\t";
        }
        System.out.println(space + dto.getNodeName());
        if(childs.size()>0){
            level ++;
        }
        for (DTONodeDetail obj : childs) {
            displayTree(obj, level, tree);
        }
    }

    public static void main(String[] args) {
    	List<DTONodeDetail> tree = new ArrayList<DTONodeDetail>();

      /*  tree.add(getDTO(1, "Root", 0));
        tree.add(getDTO(239, "Node_1", 1));
        tree.add(getDTO(242, "Node_2", 239));
        tree.add(getDTO(243, "Node_3", 239));
        tree.add(getDTO(244, "Node_4", 242));
        tree.add(getDTO(245, "Node_5", 243));*/
        displayTree(tree.get(0), 0, tree);
    }
}