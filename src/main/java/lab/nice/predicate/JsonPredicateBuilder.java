package lab.nice.predicate;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Stack;
import java.util.function.Predicate;

public class JsonPredicateBuilder implements PredicateBuilder<JsonNode, BinaryTreeNode<RuleEntry>> {

    /**
     * Build Predicate through post-order-traversal binary tree
     *
     * @param expressionTree
     * @return
     */
    @Override
    public Predicate<JsonNode> build(BinaryTreeNode<RuleEntry> expressionTree) {
        if (expressionTree == null) {
            return jsonNode -> true;
        }
        Stack<Predicate<JsonNode>> predicates = new Stack<>();
        Stack<BinaryTreeNode<RuleEntry>> stack = new Stack<>();
        BinaryTreeNode<RuleEntry> current = expressionTree, lastVisited = expressionTree;
        stack.push(current);
        while (!stack.isEmpty()) {
            current = stack.peek();
            if ((current.left == null && current.right == null)
                    || (current.right == null && lastVisited == current.left)
                    || (lastVisited == current.right)) {
                //build predicate here
                System.out.println(current.value);

                lastVisited = current;
                stack.pop();
            } else {
                if (current.right != null) {
                    stack.push(current.right);
                }
                if (current.left != null) {
                    stack.push(current.left);
                }
            }
        }
        return null;
    }

    private Predicate<JsonNode> comparisonPredicate(RuleEntry ruleEntry){
        return jsonNode -> {
            Rule rule = ruleEntry.rule;
            JsonNode node = jsonNode.findPath(rule.operand);
            if (node.isMissingNode()){
                return false;
            }
            switch (rule.comparisonOperator){
                case EQUAL:

            }
            return false;
        };
    }
}
