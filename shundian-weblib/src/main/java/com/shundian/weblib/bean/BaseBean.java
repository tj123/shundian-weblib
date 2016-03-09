package com.shundian.weblib.bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shundian.weblib.bean.validate.CheckBlankCallBack;
import com.shundian.weblib.bean.validate.Checkable;
import com.shundian.weblib.bean.validate.Email;
import com.shundian.weblib.bean.validate.InvalidRegExp;
import com.shundian.weblib.bean.validate.Message;
import com.shundian.weblib.bean.validate.NotNull;
import com.shundian.weblib.bean.validate.NotValidException;
import com.shundian.weblib.bean.validate.Phone;
import com.shundian.weblib.bean.validate.Tel;
import com.shundian.weblib.bean.validate.ValidRegExp;
import com.shundian.weblib.bean.validate.Validate;
import com.shundian.weblib.bean.validate.ValidateMethod;
import com.shundian.weblib.bean.validate.ValidateUtil;
import com.shundian.weblib.bean.validate.Error;

/**
 * 用于字段验证和转为 PO Created by TJ on 2016/3/2.
 */
public abstract class BaseBean {

	Log log = LogFactory.getLog(BaseBean.class);

	/**
	 * 存在
	 * 
	 * @param annotation
	 * @return
	 */
	private boolean exist(Annotation annotation) {
		return ValidateUtil.exist(annotation);
	}

	/**
	 * 为空
	 * 
	 * @param string
	 * @return
	 */
	private boolean isBlank(String string) {
		return ValidateUtil.isBlank(string);
	}

	/**
	 * 获取数据直到不为空的数据
	 * 
	 * @param strings
	 * @return
	 */
	private String getNotNull(String... strings) {
		return ValidateUtil.getNotNull(strings);
	}

	/**
	 * 获取注解的value值
	 * 
	 * @param annotation
	 * @return
	 */
	private String getStringValue(Annotation annotation) {
		return ValidateUtil.getStringValue(annotation);
	}

	/**
	 * 获取注解的message值
	 * 
	 * @param annotation
	 * @return
	 */
	private String getMessage(Annotation annotation) {
		return ValidateUtil.getMessage(annotation);
	}

	/**
	 * 默认遇到错误就退出
	 * 
	 * @return
	 * @throws NotValidException
	 */
	public <T extends BaseBean> T validate() throws NotValidException {
		return validate(false);
	}

	/**
	 * 对 Dto进行校验
	 * 
	 * @return
	 * @throws NotValidException
	 */
	public <T extends BaseBean> T validate(final boolean checkAll) throws NotValidException {
		@SuppressWarnings("unchecked")
		final T ts = (T) this;
		Class<?> clazz = ts.getClass();
		if (!ValidateUtil.exist(clazz.getAnnotation(Validate.class))) {
			if (log.isWarnEnabled()) {
				log.warn(clazz.getName() + "忽略校验!");
			}
			return ts;
		}
		Field[] fields = clazz.getDeclaredFields();
		final NotValidException notValidException = new NotValidException();
		for (final Field field : fields) {
			field.setAccessible(true);

			final InvalidRegExp invalidRegExp = field.getAnnotation(InvalidRegExp.class);
			final Message message = field.getAnnotation(Message.class);
			final NotNull notNull = field.getAnnotation(NotNull.class);
			final ValidRegExp validRegExp = field.getAnnotation(ValidRegExp.class);
			if (exist(notNull)) {
				try {
					checkFieldBlank(field.get(ts), new CheckBlankCallBack() {

						@Override
						public void onNotBlank(String fieldValue) {

						}

						@Override
						public void onBlank() throws NotValidException {
							String msg = getNotNull(getMessage(notNull), getStringValue(message));
							if (isBlank(msg))
								msg = field.getName() + ValidateUtil.NOT_VALID_MESSAGE;
							if (checkAll) {
								notValidException.addError(new Error(field.getName(), msg));
							} else {
								throw new NotValidException(msg);
							}

						}
					});
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			if (exist(validRegExp)) {
				try {
					checkFieldBlank(field.get(ts), new CheckBlankCallBack() {

						@Override
						public void onNotBlank(String fieldValue) throws NotValidException {
							if (!fieldValue.matches(validRegExp.value())) {
								String msg = getNotNull(getMessage(validRegExp), getStringValue(message));
								if (isBlank(msg))
									msg = field.getName() + ValidateUtil.NOT_VALID_MESSAGE;
								if (checkAll) {
									notValidException.addError(new Error(field.getName(), msg));
								} else {
									throw new NotValidException(msg);
								}
							}

						}

						@Override
						public void onBlank() throws NotValidException {
							String msg = getNotNull(getMessage(validRegExp), getStringValue(message));
							if (isBlank(msg))
								msg = field.getName() + ValidateUtil.NOT_VALID_MESSAGE;
							if (checkAll) {
								notValidException.addError(new Error(field.getName(), msg));
							} else {
								throw new NotValidException(msg);
							}

						}
					});
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			if (exist(invalidRegExp)) {
				try {
					checkFieldBlank(field.get(ts), new CheckBlankCallBack() {

						@Override
						public void onNotBlank(String fieldValue) throws NotValidException {
							if (fieldValue.matches(invalidRegExp.value())) {
								String msg = getNotNull(getMessage(invalidRegExp), getStringValue(message));
								if (isBlank(msg))
									msg = field.getName() + ValidateUtil.NOT_VALID_MESSAGE;
								if (checkAll) {
									notValidException.addError(new Error(field.getName(), msg));
								} else {
									throw new NotValidException(msg);
								}
							}

						}

						@Override
						public void onBlank() throws NotValidException {
							String msg = getNotNull(getMessage(validRegExp), getStringValue(message));
							if (isBlank(msg))
								msg = field.getName() + ValidateUtil.NOT_VALID_MESSAGE;
							if (checkAll) {
								notValidException.addError(new Error(field.getName(), msg));
							} else {
								throw new NotValidException(msg);
							}

						}
					});
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			Phone phone = field.getAnnotation(Phone.class);
			Email email = field.getAnnotation(Email.class);
			Tel tel = field.getAnnotation(Tel.class);

			// 所有的需要正则执行的都在这里
			checkAnnoBehind(ValidRegExp.class, new CheckBlankCallBack() {

				@Override
				public void onNotBlank(String value) throws NotValidException {
					String msg = getNotNull(getStringValue(message));
					if (isBlank(msg))
						msg = field.getName() + ValidateUtil.NOT_VALID_MESSAGE;
					try {
						Object ob = field.get(ts);
						if (ob instanceof String) {
							if (((String) ob).matches(value))
								return;
						} else {
							return;
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {

					}
					if (checkAll) {
						notValidException.addError(new Error(field.getName(), msg));
					} else {
						throw new NotValidException(msg);
					}
				}

				@Override
				public void onBlank() throws NotValidException {

				}
			}, email, phone, tel);

			// 调用反射调用的方法
			ValidateMethod validateMethod = field.getAnnotation(ValidateMethod.class);
			if (exist(validateMethod)) {
				Class<? extends Checkable> methodClass = validateMethod.value();
				try {
					methodClass.newInstance().check(field.get(ts));
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
					e.printStackTrace();
				} catch (NotValidException e) {
					if (checkAll) {
						notValidException.addError(new Error(field.getName(), e.getMessage()));
					} else {
						throw e.setMessage(field.getName() + e.getMessage());
					}
				}
			}
			// 验证日期
			DatePattern datePattern = field.getAnnotation(DatePattern.class);
			String pattern = getStringValue(datePattern);
			if (!isBlank(pattern)) {
				Object fieldVal;
				try {
					fieldVal = field.get(ts);
					if (fieldVal instanceof String) {
						try {
							BeanConvertUtil.string2Date((String) fieldVal, pattern);
						} catch (ParseException e) {
							String msg = getNotNull(getMessage(datePattern),getStringValue(message));
							if (isBlank(msg))
								msg = field.getName() + ValidateUtil.NOT_VALID_MESSAGE;
							if (checkAll) {
								notValidException.addError(new Error(field.getName(), msg));
							} else {
								throw new NotValidException(msg);
							}
						}
					} else if (fieldVal instanceof Date) {
						BeanConvertUtil.date2String((Date) fieldVal, pattern);
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}

			}

		}
		if (notValidException.hasError())
			throw notValidException;
		return (T) ts;
	}

	/**
	 * 检查字段的值是否为空
	 * 
	 * @param value
	 * @param callback
	 * @throws NotValidException
	 */
	private void checkFieldBlank(Object value, CheckBlankCallBack callback) throws NotValidException {
		if (value == null || (value instanceof String && isBlank((String) value))) {
			callback.onBlank();
		} else if (value instanceof String) {
			callback.onNotBlank(((String) value).trim());
		} else {
			callback.onBlank();
		}
	}

	/**
	 * 读取 标注在annotations 上的 clazz
	 * 
	 * @param annotations
	 * @throws NotValidException
	 */
	private <A extends Annotation> void checkAnnoBehind(Class<A> clazz, CheckBlankCallBack callBack,
			Annotation... annotations) throws NotValidException {
		for (Annotation annotation : annotations) {
			if (exist(annotation)) {
				A anno = annotation.annotationType().getAnnotation(clazz);
				String value = getStringValue(anno);
				if (isBlank(value)) {
					callBack.onBlank();
				} else {
					callBack.onNotBlank(value);
				}
			}
		}
	}

	/**
	 * 转为 对象
	 * 
	 * @param clazz
	 * @return
	 * @throws BeanConvertException
	 */
	public <P, T extends BaseBean> P toPo(Class<P> clazz) throws BeanConvertException {
		@SuppressWarnings("unchecked")
		T ts = (T) this;
		PoClass poClass = ts.getClass().getAnnotation(PoClass.class);
		if (!exist(poClass)) {
			throw new BeanConvertException(BeanConvertUtil.PO_NOT_FOUND);
		}
		if (!poClass.value().equals(clazz)) {
			throw new BeanConvertException(BeanConvertUtil.ASSERT_FAILD);
		}
		try {
			P po = clazz.newInstance();
			Field[] fields = ts.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				DatePattern datePattern = field.getAnnotation(DatePattern.class);
				String fieldName = field.getName();
				String fieldNameUpper = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				Object orignalValue = field.get(ts);
				// 如果初始值为空的处理
				if (orignalValue == null) {
					continue;
				}
				Class<?> targetClass = clazz.getDeclaredField(fieldName).getType();
				Method setMethod = clazz.getMethod("set" + fieldNameUpper, targetClass);
				if (orignalValue.getClass().equals(targetClass)) {
					setMethod.invoke(po, orignalValue);
					continue;
				}
				if (targetClass.equals(String.class)) {
					if (orignalValue instanceof Date) {
						String pattern = getStringValue(datePattern);
						if (isBlank(pattern)) {
							pattern = BeanConvertUtil.DEFAULT_DATE_PATTERN;
						}
						setMethod.invoke(po, BeanConvertUtil.date2String((Date) orignalValue, pattern));
					} else {
						setMethod.invoke(po, BeanConvertUtil.toString(orignalValue));
					}
				} else if (targetClass.equals(Double.class) || targetClass.equals(double.class)) {
					if (orignalValue instanceof String) {
						setMethod.invoke(po, BeanConvertUtil.string2Double((String) orignalValue));
					} else {
						throw new BeanConvertException(BeanConvertUtil.UNDISPOSED_ERROR);
					}
				} else if (targetClass.equals(Integer.class) || targetClass.equals(int.class)) {
					if (orignalValue instanceof String) {
						setMethod.invoke(po, BeanConvertUtil.string2Int((String) orignalValue));
					} else {
						throw new BeanConvertException(BeanConvertUtil.UNDISPOSED_ERROR);
					}
				} else if (targetClass.equals(Date.class)) {
					if (orignalValue instanceof String) {
						String pattern = getStringValue(datePattern);
						if (isBlank(pattern)) {
							pattern = BeanConvertUtil.DEFAULT_DATE_PATTERN;
						}
						if (!isBlank((String) orignalValue))
							setMethod.invoke(po, BeanConvertUtil.string2Date((String) orignalValue, pattern));
					} else {
						throw new BeanConvertException(BeanConvertUtil.UNDISPOSED_ERROR);
					}
				} else {
					throw new BeanConvertException(BeanConvertUtil.UNDISPOSED_ERROR);
				}
			}
			return po;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 替换元素的默认值如有为 null 的设置为 ""
	 * 
	 * @return
	 */
	public <T extends BaseBean> T replaceNull() {
		@SuppressWarnings("unchecked")
		T ts = (T) this;
		Field[] fields = ts.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (field.get(ts) == null && field.getType().equals(String.class)) {
					field.set(ts, "");
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return ts;
	}

}
