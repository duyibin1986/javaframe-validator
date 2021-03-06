package cn.javaframe.validator.validators;

import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

import cn.javaframe.validator.EnumConstants.BoundryType;
import cn.javaframe.validator.bean.ValidateResult;
import cn.javaframe.validator.bean.RuleVO;

/**
 * 和当前时间比较验证器
 * 
 * @author xinchun.wang
 * 
 *         规则：<br>
 *         1、必须有,号分隔 <br>
 *         2、必须有比较字符 eg: value = ">=,yyyy-MM-dd"
 */
public class DateCompareNowValidator extends AbstractValidator {
	@Override
	public ValidateResult validate(RuleVO validator, Map<String, String> params) {
		try {
			String[] ruleValueArr = validator.getRule().split(",");
			String pattern = PATTERN_yyyy_MM_dd;
			if (ruleValueArr.length == 2) {
				pattern = ruleValueArr[1];
			}

			long paramTime = DateUtils.parseDate(params.get(validator.getProperty()), new String[]{pattern,PATTERN_yyyy_MM_dd2}).getTime();
			long nowTime = System.currentTimeMillis();

			BoundryType boundryType = resolveBoundryType(ruleValueArr[0]);
			if (numberCompare(paramTime, nowTime, boundryType)) {
				return ValidateResult.SUCCESS;
			}
		} catch (Exception e) {
			logWarn(e, validator.getProperty(),params.get(validator.getProperty()),validator.getRule(),this.getClass().getName());
		}
		return ValidateResult.errorInstance(validator.getTip());
	}
}
