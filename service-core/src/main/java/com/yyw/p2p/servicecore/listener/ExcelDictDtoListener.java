//package com.yyw.p2p.servicecore.listener;
//
//import com.alibaba.excel.context.AnalysisContext;
//import com.alibaba.excel.event.AnalysisEventListener;
//import com.yyw.p2p.servicecore.entity.dto.ExcelDictDto;
//import com.yyw.p2p.servicecore.mapper.DictMapper;
//import lombok.extern.slf4j.Slf4j;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Slf4j
//public class ExcelDictDtoListener extends AnalysisEventListener<ExcelDictDto> {
//    //我们不希望每一行数据解析成实体类对象后就立即添加到数据库中，所以通过将解析好的对象存入列表，等列表到达一定数量后再一次性全部添加到数据库中
//    private List<ExcelDictDto> list = new ArrayList();
//
//    //每5条记录就向数据库添加数据
//    static final int BATCH_LENGTH = 5;
//
//    //此时不可以直接通过注解@Autowired进行注入，因为注入的前提是当前类也被Spring容器管理。所以此时可以通过构造器传入dictMapper对象
//    private DictMapper dictMapper;
//
//    public ExcelDictDtoListener(DictMapper dictMapper) {
//        this.dictMapper = dictMapper;
//    }
//
//    public ExcelDictDtoListener() {}
//
//
//    @Override
//    public void invoke(ExcelDictDto excelDictDto, AnalysisContext analysisContext) {
//        list.add(excelDictDto);
//        if (list.size()>=5){
//            saveData();
//            list.clear();
//        }
//    }
//
//    @Override
//    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//        //将剩余的数据存入数据库中
//        saveData();
//    }
//
//
//    private void saveData() {
//        dictMapper.insertBatch(list);
//    }
//}
