package au.com.optus.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import au.com.optus.model.Counts;
import au.com.optus.model.SearchText;


@RestController
@RequestMapping("counter-api")
public class HelloWorldController {
 
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Counts search(@RequestBody SearchText text) {
    	Counts counts = new Counts();
    	List<Map<String,Integer>> list = new ArrayList<Map<String,Integer>>();
    	
    	
    	for(String s:text.getSearchText()){
    		//random generate content
    		int range = new Random().nextInt(50) + 1;
    		Map<String,Integer> map = new HashMap<String,Integer>();
    		map.put(s, range);
    		list.add(map);
    	}
    	counts.setCounts(list);
    	
        return counts;
    }
 
    @RequestMapping(value = "/top/{rank}", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String getUser(@PathVariable("rank") int rank) {
    	
    	String resultString = "";
    	int score = 100;
    	
    	for(int i=1;i<=rank;i++){
    		int range = new Random().nextInt(4);
    		resultString +=  "text" + i + "|" + (score-=range) +"\n";
    	}
    	
        return resultString;
    }
 
}