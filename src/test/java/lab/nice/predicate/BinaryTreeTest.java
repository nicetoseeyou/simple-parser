package lab.nice.predicate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class BinaryTreeTest {

    Rule ageRule = new Rule("age", ComparisonOperator.GREATER_THAN, Arrays.asList(18));
    Rule nameRule = new Rule("name", ComparisonOperator.EQUAL, Arrays.asList("Jackson"));
    Rule countryRule = new Rule("country", ComparisonOperator.IN, Arrays.asList("USA", "UK"));

    RuleEntry ageEntry = new RuleEntry(LogicOperator.NA, ageRule);
    RuleEntry nameEntry = new RuleEntry(LogicOperator.NA, nameRule);
    RuleEntry andEntry = new RuleEntry(LogicOperator.AND, null);

    @Test
    public void testSimpleType() throws IOException {
        // {"value":2,"left":{"value":1,"left":null,"right":null},"right":{"value":3,"left":null,"right":null}}
        BinaryTreeNode<Integer> left = new BinaryTreeNode<>(1);
        BinaryTreeNode<Integer> right = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(2, left, right);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(root);
        System.out.println(json);
        BinaryTreeNode<Integer> deserializedTree = mapper.readValue(json, new TypeReference<BinaryTreeNode<Integer>>() {
        });
        Assert.assertEquals(root, deserializedTree);
    }

    @Test
    public void testCustomizedSimpleType() throws IOException {
        // {"value":{"operand":"country","comparisonOperator":"IN","condition":["USA","UK"]},"left":{"value":{"operand":"name","comparisonOperator":"EQUAL","condition":["Jackson"]},"left":null,"right":null},"right":{"value":{"operand":"age","comparisonOperator":"GREATER_THAN","condition":[18]},"left":null,"right":null}}
        BinaryTreeNode<Rule> left = new BinaryTreeNode<>(nameRule);
        BinaryTreeNode<Rule> right = new BinaryTreeNode<>(ageRule);
        BinaryTreeNode<Rule> root = new BinaryTreeNode<>(countryRule, left, right);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(root);
        System.out.println(json);
        BinaryTreeNode<Rule> deserializedTree = mapper.readValue(json, new TypeReference<BinaryTreeNode<Rule>>() {
        });
        Assert.assertEquals(root, deserializedTree);
    }

    @Test
    public void testCustomizedComplexType() throws IOException {
        // {"value":{"logicOperator":"AND","rule":null},"left":{"value":{"logicOperator":"NA","rule":{"operand":"name","comparisonOperator":"EQUAL","condition":["Jackson"]}},"left":null,"right":null},"right":{"value":{"logicOperator":"NA","rule":{"operand":"age","comparisonOperator":"GREATER_THAN","condition":[18]}},"left":null,"right":null}}
        BinaryTreeNode<RuleEntry> left = new BinaryTreeNode<>(nameEntry);
        BinaryTreeNode<RuleEntry> right = new BinaryTreeNode<>(ageEntry);
        BinaryTreeNode<RuleEntry> root = new BinaryTreeNode<>(andEntry, left, right);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(root);
        System.out.println(json);
        BinaryTreeNode<RuleEntry> deserializedTree = mapper.readValue(json, new TypeReference<BinaryTreeNode<RuleEntry>>() {
        });
        Assert.assertEquals(root, deserializedTree);
    }

    @Test
    public void testReadFromFile() throws IOException {
        BinaryTreeNode<RuleEntry> left = new BinaryTreeNode<>(nameEntry);
        BinaryTreeNode<RuleEntry> right = new BinaryTreeNode<>(ageEntry);
        BinaryTreeNode<RuleEntry> root = new BinaryTreeNode<>(andEntry, left, right);

        ObjectMapper mapper = new ObjectMapper();
        BinaryTreeNode<RuleEntry> deserializedTree = mapper.readValue(this.getClass().getResource("/rule.json"), new TypeReference<BinaryTreeNode<RuleEntry>>() {
        });
        Assert.assertEquals(root, deserializedTree);
        System.out.println(deserializedTree.inorderTraversal(deserializedTree));
    }
}
