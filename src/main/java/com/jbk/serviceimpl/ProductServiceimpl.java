package com.jbk.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.dao.ProductDao;
import com.jbk.daoimpl.Productdaoimpl;
import com.jbk.entity.ProductEntity;
import com.jbk.exception.ResourceNotExistsException;
import com.jbk.exception.SomethingWentWrongException;
import com.jbk.model.CategoryModel;
import com.jbk.model.ProductModel;
import com.jbk.model.Product_Supplier_Category;
import com.jbk.model.SupplierModel;
import com.jbk.service.ProductService;
import com.jbk.utility.ObjectValidator;



@Service
public class ProductServiceimpl implements ProductService {

	
	@Autowired
	
	private RestTemplate restTemplate;
	
	@Autowired
	ProductDao dao;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ObjectValidator validator;

	
	Map<Integer, Map<String, String>> rowError = new HashMap<Integer, Map<String, String>>();
	Map<String, String> validationMap = new HashMap<String, String>();
	Map<String, Object> finalMap = new LinkedHashMap<String, Object>();
	int totalRecords = 0;
	List<Integer> rowNumList = new ArrayList<Integer>();

	@Override
	public boolean addProduct(ProductModel productModel) {
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
		productModel.setProductid(Long.parseLong(timeStamp));

		ProductEntity productEntity = mapper.map(productModel, ProductEntity.class);

		return dao.addProduct(productEntity);
	
	}

	@Override
	public ProductModel getProductById(long productid) {
		ProductEntity productEntity = dao.getProductById(productid);

		if (productEntity != null) {
			ProductModel productModel = mapper.map(productEntity, ProductModel.class);
			return productModel;
		} else {
			throw new ResourceNotExistsException("Product Not Exists ID = " + productid);
		}

	}

	
	@Override
	public boolean deleteProductById(long productId) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public boolean updateProduct(ProductModel product) {
		return dao.updateProduct(mapper.map(product, ProductEntity.class));
		
	}

	
	@Override
	public List<ProductModel> getMaxPriceProduct() {
		
		List<ProductEntity> list = dao.getMaxPriceProduct();

		List<ProductModel> modelList = new ArrayList<ProductModel>();
//		
//		for (ProductEntity productEntity : list) {
//			ProductModel productModel = mapper.map(productEntity, ProductModel.class);
//			modelList.add(productModel);
//		}

		modelList = list.stream().map(productEntity -> mapper.map(productEntity, ProductModel.class))
				.collect(Collectors.toList());

		return modelList;
	}
	

	
	@Override
	public List<ProductModel> getAllProducts() {
		List<ProductEntity> entityList = dao.getAllProducts();
		List<ProductModel> modelList = new ArrayList<ProductModel>();
		if (!entityList.isEmpty()) {

			for (ProductEntity productEntity : entityList) {

				ProductModel productModel = mapper.map(productEntity, ProductModel.class);
				modelList.add(productModel);

			}

			return modelList;
		} else {
			throw new ResourceNotExistsException("Product Not Exists");
		}
	}

	@Override
	public List<ProductModel> getAllProducts(double low, double high) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductModel> getProductStartWith(String expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double productPriceAverage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double countOfTotalProducts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProductModel> getAllProducts(long category, long supplier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductModel> getAllProducts(String supplier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductModel> sortProduct(String orderType, String property) {
		List<ProductEntity> entityList = dao.sortProduct(orderType, property);
		List<ProductModel> modelList = new ArrayList<ProductModel>();
		if (!entityList.isEmpty()) {

			for (ProductEntity productEntity : entityList) {

				ProductModel productModel = mapper.map(productEntity, ProductModel.class);
				modelList.add(productModel);

			}

			return modelList;
		} else {
			throw new ResourceNotExistsException("Product Not Exists");
		}
	
	}

	@Override
	public double getMaxProductPrice() {
		double maxProductPrice = dao.getMaxProductprice();
		if (maxProductPrice > 0) {
			return maxProductPrice;
		} else {
			throw new ResourceNotExistsException("Product Not Exists");
		}

		
	}

	@Override
	public ProductModel getProductByName(String productnm) {
		ProductEntity dbProduct = dao.getProductByName(productnm);
		if (dbProduct != null) {
			return mapper.map(dao.getProductByName(productnm), ProductModel.class);
		} else {
			return null;
		}
		
	}

	@Override
	public Map<String, Object> uploadedSheet(MultipartFile myfile)  {
		int isAddedCounter = 0;
		int alreadyExistsCounter = 0;
		int issueCounter = 0;
		try {
			String path = "uploaded";
			String fileName = myfile.getOriginalFilename();
			FileOutputStream fos = new FileOutputStream(path + File.separator + fileName);

			byte[] data = myfile.getBytes();

			fos.write(data);

			// read excell
			List<ProductModel> list = readExcel(path + File.separator + fileName);

			for (ProductModel productModel : list) {
				ProductEntity productEntity = mapper.map(productModel, ProductEntity.class);

				try {
					boolean isAdded = dao.addProduct(productEntity);
					if (isAdded) {
						isAddedCounter = isAddedCounter + 1;
					}
				} catch (SomethingWentWrongException e) {
					issueCounter = issueCounter + 1;
				}
			}

			finalMap.put("Total Records In Sheet", totalRecords);
			finalMap.put("Uploaded Record In DB", isAddedCounter);
			finalMap.put("Total Exists Record In DB", rowNumList.size());
			finalMap.put("Rows Num, Exists Record In DB", rowNumList);
			finalMap.put("Total Excluded Record Count", rowError.size());
			finalMap.put("Bad Record Row Number", rowError);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return finalMap;
	}

	private List<ProductModel> readExcel(String filePath)  {
		List<ProductModel> list = new ArrayList<>();
		
		try {
			FileInputStream fis = new FileInputStream(filePath);

			
			Workbook workbook = WorkbookFactory.create(fis);

			
			Sheet sheet = workbook.getSheetAt(0);

			totalRecords = sheet.getLastRowNum();

			
			Iterator<Row> rows = sheet.rowIterator();

			

			while (rows.hasNext()) {
				Row row = rows.next();
				int rowNum = row.getRowNum();
				if (rowNum == 0) {
					continue;
				}

				
				ProductModel productModel = new ProductModel();

				String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
				productModel.setProductid(Long.parseLong(timeStamp) + rowNum);

				
				Iterator<Cell> cells = row.cellIterator();

				while (cells.hasNext()) {
					Cell cell = cells.next();

				
					int columnIndex = cell.getColumnIndex();
					switch (columnIndex) {
					case 0: {
						String productnm = cell.getStringCellValue();
						productModel.setProductnm(productnm);
						break;
					}
					case 1: {
						double supplierid = cell.getNumericCellValue();
						
						productModel.setSupplierid((long)cell.getNumericCellValue());
						break;
					}
					case 2: {
						double categoryid = cell.getNumericCellValue();
						
						productModel.setCategoryid((long)cell.getNumericCellValue());
						break;
					}
					case 3: {
						double productqty = cell.getNumericCellValue();
						productModel.setProductqty((int) productqty);
						break;
					}
					case 4: {
						double productprice = cell.getNumericCellValue();
						productModel.setProductprice(productprice);
						break;
					}
					case 5: {
						double charges = cell.getNumericCellValue();
						productModel.setDeliverycharges((int) charges);
						break;
					}

					}

				}

				// check data is proper or not
				validationMap = validator.validateProduct(productModel);

				if (!validationMap.isEmpty()) {
					// add validatio map with row num
					rowError.put(rowNum + 1, validationMap);
				} else {
					// check its exist or not in DB
					ProductModel dbProduct = getProductByName(productModel.getProductnm());
					if (dbProduct != null) {
						rowNumList.add(rowNum + 1);
					} else {
						list.add(productModel);
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Product_Supplier_Category getProductWithSCByPId(long productid) {
		Product_Supplier_Category psc=null;
		
		
		ProductModel productModel=getProductById(productid);
		if(productModel !=null) {
			psc=new Product_Supplier_Category();
			psc.setProductModel(productModel);
			try {
				SupplierModel supplierModel = restTemplate.getForObject("http://localhost:8082/supplier/get-supplier-by-id/"+productModel.getSupplierid(), SupplierModel.class);
				psc.setSupplierModel(supplierModel)	;
				
			}catch(ResourceAccessException e) {
				psc.setSupplierModel(null);
			}
			try {
				CategoryModel categoryModel=restTemplate.getForObject("http://localhost:8081/category/get-category-by-id/"+productModel.getCategoryid(), CategoryModel.class);
				psc.setCategoryModel(categoryModel);
				if(categoryModel.getCategoryid()<=0) {
					psc.setCategoryModel(null);
				}
				else {
					psc.setCategoryModel(null);
				}
			}catch(ResourceAccessException e) {
				psc.setCategoryModel(null);
			}
//			catch(Exception e) {
//				psc.setCategoryModel(null);
//			}
		}else {
			
			
		}
		
	
		return psc;
	}
		

	}

	
	
	
	
	
	
			
			
		

	
