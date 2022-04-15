import { entityItemSelector } from '../../support/commands';
import {
  entityTableSelector,
  entityDetailsButtonSelector,
  entityDetailsBackButtonSelector,
  entityCreateButtonSelector,
  entityCreateSaveButtonSelector,
  entityCreateCancelButtonSelector,
  entityEditButtonSelector,
  entityDeleteButtonSelector,
  entityConfirmDeleteButtonSelector,
} from '../../support/entity';

describe('Payement e2e test', () => {
  const payementPageUrl = '/payement';
  const payementPageUrlPattern = new RegExp('/payement(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const payementSample = {};

  let payement: any;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/payements+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/payements').as('postEntityRequest');
    cy.intercept('DELETE', '/api/payements/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (payement) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/payements/${payement.id}`,
      }).then(() => {
        payement = undefined;
      });
    }
  });

  it('Payements menu should load Payements page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('payement');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response!.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Payement').should('exist');
    cy.url().should('match', payementPageUrlPattern);
  });

  describe('Payement page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(payementPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Payement page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/payement/new$'));
        cy.getEntityCreateUpdateHeading('Payement');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', payementPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/payements',
          body: payementSample,
        }).then(({ body }) => {
          payement = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/payements+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [payement],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(payementPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Payement page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('payement');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', payementPageUrlPattern);
      });

      it('edit button click should load edit Payement page', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Payement');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', payementPageUrlPattern);
      });

      it('last delete button click should delete instance of Payement', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('payement').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', payementPageUrlPattern);

        payement = undefined;
      });
    });
  });

  describe('new Payement page', () => {
    beforeEach(() => {
      cy.visit(`${payementPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('Payement');
    });

    it('should create an instance of Payement', () => {
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(201);
        payement = response!.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(200);
      });
      cy.url().should('match', payementPageUrlPattern);
    });
  });
});
