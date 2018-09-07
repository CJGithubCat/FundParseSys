package test.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义spirng事件
 */
public class TradeEvent extends ApplicationEvent{

	
	private static final long serialVersionUID = 7122402219477234978L;

	public TradeEvent(Object source) {
		super(source);
		System.out.println(source);
	}

}
