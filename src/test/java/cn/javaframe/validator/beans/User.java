package cn.javaframe.validator.beans;

import cn.javaframe.validator.EnumConstants.RuleType;
import cn.javaframe.validator.annotation.ConclusionRule;
import cn.javaframe.validator.annotation.ConditionRule;
import cn.javaframe.validator.annotation.LogicRule;
import cn.javaframe.validator.annotation.Rules;

public class User {
	@Rules(
			conclusionList = {
					@ConclusionRule(id = "A",type = RuleType.not_empty,tip = "不能为空"),
					@ConclusionRule(id = "B",type = RuleType.number_value_limit,value = "[100,150]",tip = "不在范围之内")
			},
			logicList = {
					@LogicRule(conclusion = "A"),
					@LogicRule(conclusion = "B")
			},
			text = "身高")
	private int height;
	
	@Rules(
			conclusionList = {
					@ConclusionRule(id = "A",type = RuleType.not_empty,tip = "不能为空"),
					@ConclusionRule(id = "B",type = RuleType.number_value_limit,value = "[6,15]",tip = "不在范围之内")
			},
			logicList = {
					@LogicRule(conclusion = "A"),
					@LogicRule(conclusion = "B")
			},
			text = "年龄")
	private int age;
	
	
	@Rules(
			conditionList = {
				@ConditionRule(id = "D", type = RuleType.number_value_limit, dependProperty = "age", value = "[10,15]"),
				@ConditionRule(id = "E", type = RuleType.number_value_limit, dependProperty = "height", value = "[120,150]"),
				@ConditionRule(id = "F", type = RuleType.number_value_limit, dependProperty = "age", value = "[6,9]"),
				@ConditionRule(id = "G", type = RuleType.number_value_limit, dependProperty = "height", value = "[100,119]") 
			}, 
			conclusionList = {
				@ConclusionRule(id = "A", type = RuleType.values_collection_limit, value = "0,1", tip = "不在范围之内"),
				@ConclusionRule(id = "B", type = RuleType.values_collection_limit, value = "1", tip = "身高在120厘米以上并且10到15岁之间的儿童必须参加考试"),
				@ConclusionRule(id = "C", type = RuleType.values_collection_limit, value = "0", tip = "身高在120厘米以下或者6到15岁之间的儿童不能参加考试") 
			}, 
			logicList = {
				@LogicRule(conclusion = "A"),
				@LogicRule(condition = "(F||G)&&A", conclusion = "C") 
			}, 
			text = "是否参加体育考试")
	boolean needGoSchool; //我们假设学生在

	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isNeedGoSchool() {
		return needGoSchool;
	}

	public void setNeedGoSchool(boolean needGoSchool) {
		this.needGoSchool = needGoSchool;
	}
}
