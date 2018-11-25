package top.kid297.redisnodes.demo.service.impl;

import org.springframework.stereotype.Service;
import top.kid297.redisnodes.demo.service.DatebaseService;

@Service
public class DatebaseServiceImpl implements DatebaseService {
    @Override
    public String queryGoodsNum(String goodsSku) {
        switch (goodsSku){
            case "10001":
                return "99";
            case "10002":
                return "100";
            case "10003":
                return "105";
                default:
                    return "99";
        }
    }
}
