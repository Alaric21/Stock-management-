package fr.eitelalaric.gestiondestock.service.impl;

import fr.eitelalaric.gestiondestock.dto.MvtStkDto;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidEntityException;
import fr.eitelalaric.gestiondestock.model.TypeMvtStk;
import fr.eitelalaric.gestiondestock.repository.MvtStkRepo;
import fr.eitelalaric.gestiondestock.service.MvtStkService;
import fr.eitelalaric.gestiondestock.service.ProductService;
import fr.eitelalaric.gestiondestock.validator.MvtStkValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MvtStkServiceImpl implements MvtStkService {

    private final MvtStkRepo mvtStkRepo;

    private final ProductService productService;

    public MvtStkServiceImpl(MvtStkRepo mvtStkRepo, ProductService productService) {
        this.mvtStkRepo = mvtStkRepo;
        this.productService = productService;
    }

    @Override
    public BigDecimal stockReelProduct(Integer idProduct) {
        if (idProduct == null ){
            log.warn("ID product is null");
            return BigDecimal.valueOf(-1);
        }
        productService.findById(idProduct);
        return mvtStkRepo.stockReelProduct(idProduct);
    }

    @Override
    public List<MvtStkDto> mvtStkProduct(Integer idProduct) {
        return mvtStkRepo.findAllByProductId(idProduct)
                .stream()
                .map(MvtStkDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MvtStkDto entreeStock(MvtStkDto mvtStkDto) {
       return entreePositive(mvtStkDto,TypeMvtStk.ENTREE);
    }

    @Override
    public MvtStkDto sortieStock(MvtStkDto mvtStkDto) {
        return  sortieNegative(mvtStkDto, TypeMvtStk.SORTIE);
    }

    @Override
    public MvtStkDto correctionStockPositive(MvtStkDto mvtStkDto) {
       return entreePositive(mvtStkDto,TypeMvtStk.CORRECTION_POSITIVE);
    }

    @Override
    public MvtStkDto correctionStockNegative(MvtStkDto mvtStkDto) {
        return  sortieNegative(mvtStkDto, TypeMvtStk.CORRECTION_NEGATIVE);
    }

    public MvtStkDto entreePositive(MvtStkDto mvtStkDto, TypeMvtStk typeMvtStk) {
        List<String> errors = MvtStkValidator.validate(mvtStkDto);

        if (!errors.isEmpty()) {
            log.error("MvtStk not valid: {}", mvtStkDto);
            throw new InvalidEntityException("le mouvement du stock n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID,errors);
        }
        mvtStkDto.setQuantite(BigDecimal.valueOf(Math.abs(mvtStkDto.getQuantite().doubleValue()) * -1));
        mvtStkDto.setTypeMvtStk(typeMvtStk);

        return MvtStkDto.fromEntity(mvtStkRepo.save(MvtStkDto.toEntity(mvtStkDto)));

    }

    public MvtStkDto sortieNegative(MvtStkDto mvtStkDto, TypeMvtStk typeMvtStk) {
        List<String> errors = MvtStkValidator.validate(mvtStkDto);

        if (!errors.isEmpty()) {
            log.error("MvtStk not valid: {}", mvtStkDto);
            throw new InvalidEntityException("le mouvement du stock n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID,errors);
        }
        mvtStkDto.setQuantite(BigDecimal.valueOf(Math.abs(mvtStkDto.getQuantite().doubleValue()) * -1));
        mvtStkDto.setTypeMvtStk(TypeMvtStk.CORRECTION_NEGATIVE);

        return MvtStkDto.fromEntity(mvtStkRepo.save(MvtStkDto.toEntity(mvtStkDto)));
    }
}