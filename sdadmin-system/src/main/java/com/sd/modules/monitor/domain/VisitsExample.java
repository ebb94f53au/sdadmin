package com.sd.modules.monitor.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VisitsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VisitsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(String value) {
            addCriterion("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(String value) {
            addCriterion("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(String value) {
            addCriterion("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(String value) {
            addCriterion("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(String value) {
            addCriterion("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(String value) {
            addCriterion("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLike(String value) {
            addCriterion("date like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotLike(String value) {
            addCriterion("date not like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<String> values) {
            addCriterion("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<String> values) {
            addCriterion("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(String value1, String value2) {
            addCriterion("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(String value1, String value2) {
            addCriterion("date not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andIpCountsIsNull() {
            addCriterion("ip_counts is null");
            return (Criteria) this;
        }

        public Criteria andIpCountsIsNotNull() {
            addCriterion("ip_counts is not null");
            return (Criteria) this;
        }

        public Criteria andIpCountsEqualTo(Long value) {
            addCriterion("ip_counts =", value, "ipCounts");
            return (Criteria) this;
        }

        public Criteria andIpCountsNotEqualTo(Long value) {
            addCriterion("ip_counts <>", value, "ipCounts");
            return (Criteria) this;
        }

        public Criteria andIpCountsGreaterThan(Long value) {
            addCriterion("ip_counts >", value, "ipCounts");
            return (Criteria) this;
        }

        public Criteria andIpCountsGreaterThanOrEqualTo(Long value) {
            addCriterion("ip_counts >=", value, "ipCounts");
            return (Criteria) this;
        }

        public Criteria andIpCountsLessThan(Long value) {
            addCriterion("ip_counts <", value, "ipCounts");
            return (Criteria) this;
        }

        public Criteria andIpCountsLessThanOrEqualTo(Long value) {
            addCriterion("ip_counts <=", value, "ipCounts");
            return (Criteria) this;
        }

        public Criteria andIpCountsIn(List<Long> values) {
            addCriterion("ip_counts in", values, "ipCounts");
            return (Criteria) this;
        }

        public Criteria andIpCountsNotIn(List<Long> values) {
            addCriterion("ip_counts not in", values, "ipCounts");
            return (Criteria) this;
        }

        public Criteria andIpCountsBetween(Long value1, Long value2) {
            addCriterion("ip_counts between", value1, value2, "ipCounts");
            return (Criteria) this;
        }

        public Criteria andIpCountsNotBetween(Long value1, Long value2) {
            addCriterion("ip_counts not between", value1, value2, "ipCounts");
            return (Criteria) this;
        }

        public Criteria andPvCountsIsNull() {
            addCriterion("pv_counts is null");
            return (Criteria) this;
        }

        public Criteria andPvCountsIsNotNull() {
            addCriterion("pv_counts is not null");
            return (Criteria) this;
        }

        public Criteria andPvCountsEqualTo(Long value) {
            addCriterion("pv_counts =", value, "pvCounts");
            return (Criteria) this;
        }

        public Criteria andPvCountsNotEqualTo(Long value) {
            addCriterion("pv_counts <>", value, "pvCounts");
            return (Criteria) this;
        }

        public Criteria andPvCountsGreaterThan(Long value) {
            addCriterion("pv_counts >", value, "pvCounts");
            return (Criteria) this;
        }

        public Criteria andPvCountsGreaterThanOrEqualTo(Long value) {
            addCriterion("pv_counts >=", value, "pvCounts");
            return (Criteria) this;
        }

        public Criteria andPvCountsLessThan(Long value) {
            addCriterion("pv_counts <", value, "pvCounts");
            return (Criteria) this;
        }

        public Criteria andPvCountsLessThanOrEqualTo(Long value) {
            addCriterion("pv_counts <=", value, "pvCounts");
            return (Criteria) this;
        }

        public Criteria andPvCountsIn(List<Long> values) {
            addCriterion("pv_counts in", values, "pvCounts");
            return (Criteria) this;
        }

        public Criteria andPvCountsNotIn(List<Long> values) {
            addCriterion("pv_counts not in", values, "pvCounts");
            return (Criteria) this;
        }

        public Criteria andPvCountsBetween(Long value1, Long value2) {
            addCriterion("pv_counts between", value1, value2, "pvCounts");
            return (Criteria) this;
        }

        public Criteria andPvCountsNotBetween(Long value1, Long value2) {
            addCriterion("pv_counts not between", value1, value2, "pvCounts");
            return (Criteria) this;
        }

        public Criteria andWeekDayIsNull() {
            addCriterion("week_day is null");
            return (Criteria) this;
        }

        public Criteria andWeekDayIsNotNull() {
            addCriterion("week_day is not null");
            return (Criteria) this;
        }

        public Criteria andWeekDayEqualTo(String value) {
            addCriterion("week_day =", value, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekDayNotEqualTo(String value) {
            addCriterion("week_day <>", value, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekDayGreaterThan(String value) {
            addCriterion("week_day >", value, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekDayGreaterThanOrEqualTo(String value) {
            addCriterion("week_day >=", value, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekDayLessThan(String value) {
            addCriterion("week_day <", value, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekDayLessThanOrEqualTo(String value) {
            addCriterion("week_day <=", value, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekDayLike(String value) {
            addCriterion("week_day like", value, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekDayNotLike(String value) {
            addCriterion("week_day not like", value, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekDayIn(List<String> values) {
            addCriterion("week_day in", values, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekDayNotIn(List<String> values) {
            addCriterion("week_day not in", values, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekDayBetween(String value1, String value2) {
            addCriterion("week_day between", value1, value2, "weekDay");
            return (Criteria) this;
        }

        public Criteria andWeekDayNotBetween(String value1, String value2) {
            addCriterion("week_day not between", value1, value2, "weekDay");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}