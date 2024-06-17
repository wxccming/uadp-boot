package cn.iocoder.yudao.module.bookstore.service.bookqtcodeinfo;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo.BookQtcodeInfoPageReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo.BookQtcodeInfoSaveReqVO;
import cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo.ExtraBookQtcodeInfoSaveReqVO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodeinfo.BookQtcodeInfoDO;
import cn.iocoder.yudao.module.bookstore.dal.dataobject.bookqtcodesource.BookQtcodeSourceDO;
import cn.iocoder.yudao.module.bookstore.dal.mysql.bookqtcodeinfo.BookQtcodeInfoMapper;
import cn.iocoder.yudao.module.bookstore.dal.mysql.bookqtcodesource.BookQtcodeSourceMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.infra.enums.BookStoreErrorCodeConstants.BOOK_QTCODE_INFO_NOT_EXISTS;

/**
 * 图书二维码信息 Service 实现类
 *
 * @author 管理员
 */
@Service
@Validated
public class BookQtcodeInfoServiceImpl implements BookQtcodeInfoService {

    @Resource
    private BookQtcodeInfoMapper bookQtcodeInfoMapper;

    @Resource
    private BookQtcodeSourceMapper bookQtcodeSourceMapper;

    @Override
    public Long createBookQtcodeInfo(BookQtcodeInfoSaveReqVO createReqVO) {
        BookQtcodeInfoDO bookQtcodeInfo = BeanUtils.toBean(createReqVO, BookQtcodeInfoDO.class);
        bookQtcodeInfo.setDeptId(SecurityFrameworkUtils.getLoginUserDeptId());
        bookQtcodeInfo.setDtcodeContext("");
        // 插入
        bookQtcodeInfoMapper.insert(bookQtcodeInfo);
        // 返回
        return bookQtcodeInfo.getId();
    }

    @Override
    public void updateBookQtcodeInfo(BookQtcodeInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateBookQtcodeInfoExists(updateReqVO.getId());
        // 更新
        BookQtcodeInfoDO updateObj = BeanUtils.toBean(updateReqVO, BookQtcodeInfoDO.class);
        updateObj.setDtcodeContext("");
        bookQtcodeInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteBookQtcodeInfo(Long id) {
        // 校验存在
        validateBookQtcodeInfoExists(id);
        // 删除
        bookQtcodeInfoMapper.deleteById(id);
    }

    private void validateBookQtcodeInfoExists(Long id) {
        if (bookQtcodeInfoMapper.selectById(id) == null) {
            throw exception(BOOK_QTCODE_INFO_NOT_EXISTS);
        }
    }

    @Override
    public BookQtcodeInfoDO getBookQtcodeInfo(Long id) {
        return bookQtcodeInfoMapper.selectById(id);
    }

    @Override
    public PageResult<BookQtcodeInfoDO> getBookQtcodeInfoPage(BookQtcodeInfoPageReqVO pageReqVO) {
        return bookQtcodeInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public String genQrCode(String dtcodeAddress) {
        if(StringUtils.isBlank(dtcodeAddress)){
            return "";
        }
        //生成二维码
        QrConfig config = new QrConfig();
        return QrCodeUtil.generateAsBase64(dtcodeAddress, config, ImgUtil.IMAGE_TYPE_PNG);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveBookQtcodeInfoAndQtcodeResource(ExtraBookQtcodeInfoSaveReqVO reqVO) {
        BookQtcodeInfoDO bookQtcodeInfo = BeanUtils.toBean(reqVO, BookQtcodeInfoDO.class);
        bookQtcodeInfo.setDeptId(SecurityFrameworkUtils.getLoginUserDeptId());
        bookQtcodeInfo.setDtcodeContext("");
        // 插入
        int qtCodeId = bookQtcodeInfoMapper.insert(bookQtcodeInfo);
        Collection<BookQtcodeSourceDO> entities = new ArrayList<>();
        reqVO.getSimpleBookQtcodeSourceVO().forEach(vo ->{
            BookQtcodeSourceDO bookQtcodeSource = new BookQtcodeSourceDO();
            bookQtcodeSource.setBookNo(reqVO.getBookNo());
            bookQtcodeSource.setChapterId(reqVO.getBookNo());
            bookQtcodeSource.setSourceForm(vo.getSourceForm());
            bookQtcodeSource.setSourceName(vo.getSourceName());
            bookQtcodeSource.setApplicaScens(vo.getApplicaScens());
            bookQtcodeSource.setDtcodeId((long) qtCodeId);
            bookQtcodeSource.setSourceId(vo.getSourceId());
            bookQtcodeSource.setDeptId(SecurityFrameworkUtils.getLoginUserDeptId());
            entities.add(bookQtcodeSource);
        });
        bookQtcodeSourceMapper.insertBatch(entities);
    }
}