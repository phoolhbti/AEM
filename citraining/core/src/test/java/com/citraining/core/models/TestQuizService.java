package com.citraining.core.models;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.apache.sling.api.resource.ValueMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.adobe.cq.commerce.common.ValueMapDecorator;
import com.citraining.core.services.QuizService;

//public class TestQuizService {
//	private QuizService quizService;
//	private ValueMap properties;
//	 @Before
//	    public void setup() throws Exception {
//		// Create a mock of the WCMUse class.
//		//quizService = mock(QuizService.class);
// 
//        // Create test objects for mocking (properties ValueMap in this case).
//       // properties = new ValueMapDecorator(new HashMap<>());
// 
//        // Set what to use when superclass methods are called.
//    //    when(quizService.getProperties()).thenReturn(properties);
// 
//        // Set to call the real methods in the class being tested. For methods that return something.
//      //  when(quizService.getMessage()).thenCallRealMethod();
//       // when(quizService.getType()).thenCallRealMethod();
// 
//      // Set to call the real activate method in the class being tested. For void return types.
//      //  doCallRealMethod().when(quizService).activate();
//      //  doCallRealMethod().when(quizService).getMessage();
//	}
//	@Test
//	public void testGetMessage(){
//		String testMessage = "test message..";
//        properties.put("message", testMessage);
//        quizService.activate();
//        //String message = quizService.getMessage();
//       // assertEquals(testMessage, message);
//	}
//
//}
